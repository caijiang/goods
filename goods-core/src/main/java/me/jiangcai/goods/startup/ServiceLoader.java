/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.startup;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 它负责载入具体应用程序
 * 总体而言，它将建立2个Servlet
 * 一个是负责全局处理的工作Servlet
 * 另一个是负责维护工作Servlet
 *
 * @author CJ
 */
@SuppressWarnings("WeakerAccess")
public abstract class ServiceLoader extends AbstractContextLoaderInitializer {

    /**
     * @return 包含 {@link me.jiangcai.goods.core.config.ServiceConfig}的商品核心配置Class
     */
    protected abstract Class<?>[] getGoodsServletConfigClasses();

    protected ConfigurableWebApplicationContext createGoodsServletApplicationContext() {
        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        Class<?>[] configClasses = getGoodsServletConfigClasses();
        if (!ObjectUtils.isEmpty(configClasses)) {
            servletAppContext.register(configClasses);
        }
        return servletAppContext;
    }

    protected FrameworkServlet createGoodsDispatcherServlet(WebApplicationContext servletAppContext) {
        return new DispatcherServlet(servletAppContext);
    }

    protected ApplicationContextInitializer<?>[] getGoodsServletApplicationContextInitializers() {
        return null;
    }

    protected Filter[] getGoodsServletFilters() {
        return new Filter[]{new CharacterEncodingFilter("utf-8")};
    }

    protected boolean isGoodsServletAsyncSupported() {
        return true;
    }

    protected void customizeGoodsServletRegistration(ServletRegistration.Dynamic registration) {
    }
///// 管理

    /**
     * @return 从此产生的配置必然可以获得一个 {@link Startup}的实例
     */
    protected abstract Class<?>[] getManageServletConfigClasses();

    protected ConfigurableWebApplicationContext createManageServletApplicationContext() {
        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        Class<?>[] configClasses = getManageServletConfigClasses();
        if (!ObjectUtils.isEmpty(configClasses)) {
            servletAppContext.register(configClasses);
        }
        return servletAppContext;
    }

    protected FrameworkServlet createManageDispatcherServlet(WebApplicationContext servletAppContext) {
        return new DispatcherServlet(servletAppContext);
    }

    protected ApplicationContextInitializer<?>[] getManageServletApplicationContextInitializers() {
        return null;
    }

    protected Filter[] getManageServletFilters() {
        return new Filter[]{new CharacterEncodingFilter("utf-8")};
    }

    protected boolean isManageServletAsyncSupported() {
        return true;
    }

    protected void customizeManageServletRegistration(ServletRegistration.Dynamic registration) {
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // 部署核心商城服务
        ConfigurableWebApplicationContext goodsContext = registerDispatcherServlet("goodsServlet"
                , this::createGoodsServletApplicationContext
                , this::createGoodsDispatcherServlet, this::getGoodsServletApplicationContextInitializers
                , 1, () -> new String[]{"/"}, this::getGoodsServletFilters
                , this::isGoodsServletAsyncSupported
                , this::customizeGoodsServletRegistration
                , servletContext);

        //<editor-fold desc="这里是一个钩子，负责将核心服务的上下文丢给管理服务">
        ApplicationContextInitializer<?>[] manageInitializer = getManageServletApplicationContextInitializers();
        final ApplicationContextInitializer<ConfigurableApplicationContext> goodsContextUpdater = applicationContext
                -> {
            applicationContext.addApplicationListener(new ApplicationListener<ContextStartedEvent>() {
                @Override
                public void onApplicationEvent(ContextStartedEvent event) {
                    Startup startup = event.getApplicationContext().getBean(Startup.class);
                    startup.updateGoodsContext(goodsContext);
                }
            });

        };
        ApplicationContextInitializer<?>[] toUseManageInitializer;
        if (manageInitializer != null) {
            ApplicationContextInitializer<?>[] newManageInitializer = new ApplicationContextInitializer[manageInitializer.length + 1];
            System.arraycopy(manageInitializer, 0, newManageInitializer, 1, manageInitializer.length);

            newManageInitializer[0] = goodsContextUpdater;
            toUseManageInitializer = newManageInitializer;
        } else {
            toUseManageInitializer = new ApplicationContextInitializer[]{
                    goodsContextUpdater
            };
        }
        //</editor-fold>

        // 部署管理服务
        registerDispatcherServlet("manageServlet", this::createManageServletApplicationContext
                , this::createManageDispatcherServlet
                , () -> toUseManageInitializer, 1
                , () -> new String[]{"/manage"}, this::getManageServletFilters, this::isManageServletAsyncSupported
                , this::customizeManageServletRegistration, servletContext);

    }

    protected ConfigurableWebApplicationContext registerDispatcherServlet(String servletName
            , Supplier<ConfigurableWebApplicationContext> createServletApplicationContext
            , Function<ConfigurableWebApplicationContext, FrameworkServlet> createDispatcherServlet
            , Supplier<ApplicationContextInitializer<?>[]> getServletApplicationContextInitializers
            , int servletLoadOnStartup
            , Supplier<String[]> getServletMappings
            , Supplier<Filter[]> getServletFilters
            , Supplier<Boolean> isAsyncSupported
            , Consumer<ServletRegistration.Dynamic> customizeRegistration
            , ServletContext servletContext) {
        Assert.hasLength(servletName, "getServletName() must not return empty or null");

        ConfigurableWebApplicationContext servletAppContext = createServletApplicationContext.get();
        Assert.notNull(servletAppContext,
                "createServletApplicationContext() did not return an application " +
                        "context for servlet [" + servletName + "]");

        FrameworkServlet dispatcherServlet = createDispatcherServlet.apply(servletAppContext);
        dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers.get());

        ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
        Assert.notNull(registration,
                "Failed to register servlet with name '" + servletName + "'." +
                        "Check if there is another servlet registered under the same name.");

        registration.setLoadOnStartup(servletLoadOnStartup);
        registration.addMapping(getServletMappings.get());
        registration.setAsyncSupported(isAsyncSupported.get());

        Filter[] filters = getServletFilters.get();
        if (!ObjectUtils.isEmpty(filters)) {
            for (Filter filter : filters) {
                registerServletFilter(servletContext, filter, isAsyncSupported, servletName);
            }
        }

        customizeRegistration.accept(registration);
        return servletAppContext;
    }

    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter
            , Supplier<Boolean> isAsyncSupported, String servletName) {
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            int counter = -1;
            while (counter == -1 || registration == null) {
                counter++;
                registration = servletContext.addFilter(filterName + "#" + counter, filter);
                Assert.isTrue(counter < 100,
                        "Failed to register filter '" + filter + "'." +
                                "Could the same Filter instance have been registered already?");
            }
        }
        registration.setAsyncSupported(isAsyncSupported.get());
        registration.addMappingForServletNames(getDispatcherTypes(isAsyncSupported), false, servletName);
        return registration;
    }

    private EnumSet<DispatcherType> getDispatcherTypes(Supplier<Boolean> isAsyncSupported) {
        return (isAsyncSupported.get() ?
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC) :
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE));
    }

}
