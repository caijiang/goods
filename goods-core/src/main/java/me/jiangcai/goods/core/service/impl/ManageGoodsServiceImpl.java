/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.service.impl;

import me.jiangcai.goods.Goods;
import me.jiangcai.goods.GoodsImage;
import me.jiangcai.goods.Seller;
import me.jiangcai.goods.TradeEntity;
import me.jiangcai.goods.core.entity.SimpleGoodsImage;
import me.jiangcai.goods.image.ImageUsage;
import me.jiangcai.goods.image.ScaledImage;
import me.jiangcai.goods.service.ManageGoodsService;
import me.jiangcai.lib.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author CJ
 */
@Service
public class ManageGoodsServiceImpl implements ManageGoodsService {

    @Autowired
    private ResourceService resourceService;
//    @Autowired
//    private GoodsRepository goodsRepository;

    @Override
    public Goods addGoods(Supplier<Goods> goodsSupplier, Function<Goods, Goods> goodsSaver, Seller seller, TradeEntity tradeEntity, String name
            , BigDecimal price, Supplier<GoodsImage> goodsImageSupplier, String... imagePaths) throws IOException {
        Goods goods = goodsSupplier.get();
        goods.setSeller(seller);
        goods.setOwner(tradeEntity);
        goods.setName(name);
        goods.setPrice(price);

// 创建图片
        for (String path : imagePaths) {
            GoodsImage image;
            if (goodsImageSupplier == null) {
                SimpleGoodsImage simpleGoodsImage = new SimpleGoodsImage();
                simpleGoodsImage.setImageSet(new HashSet<>());
                image = simpleGoodsImage;
            } else
                image = goodsImageSupplier.get();

            ScaledImage scaledImage = new ScaledImage();
            int dotIndex = path.lastIndexOf('.');
            scaledImage.setFormat(path.substring(dotIndex).toUpperCase(Locale.ENGLISH));
            try (InputStream inputStream = resourceService.getResource(path).getInputStream()) {
                BufferedImage image1 = ImageIO.read(inputStream);
                scaledImage.setHeight(image1.getHeight());
                scaledImage.setWidth(image1.getWidth());
                scaledImage.setUsage(ImageUsage.preview);
            }

            String newPath = "goods_images/" + UUID.randomUUID().toString() + scaledImage.getFormat();
            // 执行转存
            try (InputStream inputStream = resourceService.getResource(path).getInputStream()) {
                resourceService.uploadResource(newPath, inputStream);
                scaledImage.setResourcePath(newPath);
            }
            resourceService.deleteResource(path);

            image.addScaledImage(scaledImage);

            goods.addGoodsImage(image);
        }

        return goodsSaver.apply(goods);
    }

    @Override
    public Goods addGoods(Supplier<Goods> goodsSupplier, Function<Goods, Goods> goodsSaver, Seller seller
            , TradeEntity tradeEntity, String name, BigDecimal price, String... imagePaths) throws IOException {
        return addGoods(goodsSupplier, goodsSaver, seller, tradeEntity, name, price, SimpleGoodsImage::new, imagePaths);
    }

    @Override
    public Goods enableGoods(Goods goods, Function<Goods, Goods> goodsSaver) {
        goods.setEnable(true);
        return goodsSaver.apply(goods);
    }
}
