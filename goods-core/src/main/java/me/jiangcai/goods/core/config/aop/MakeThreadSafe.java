/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.config.aop;

import me.jiangcai.goods.lock.GoodsThreadSafeLocker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author CJ
 */
@Aspect
public class MakeThreadSafe {

    private static final Log log = LogFactory.getLog(MakeThreadSafe.class);

    @Pointcut("@annotation(me.jiangcai.goods.lock.GoodsThreadSafe)")
    public void goodsThreadSafe() {
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    @Around("goodsThreadSafe()")
    public Object aroundSave(ProceedingJoinPoint pjp) throws Throwable {
        final Object lock = findLock(pjp.getArgs());
        log.debug("prepare invoke goodsThreadSafe for locker " + lock);
        try {
            synchronized (lock) {
                log.debug("entering locker " + lock);
                return pjp.proceed(pjp.getArgs());
            }
        } finally {
            log.debug("releasing locker " + lock);
        }

    }

    private Object findLock(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof GoodsThreadSafeLocker)
                return ((GoodsThreadSafeLocker) arg).lockObject();
        }
        return args[0];
    }

}
