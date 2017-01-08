/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.repository;

import me.jiangcai.goods.demo.entity.DemoGoods;
import me.jiangcai.goods.demo.entity.GoodsCode;
import me.jiangcai.goods.demo.entity.pk.GoodsCodePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author CJ
 */
public interface GoodsCodeRepository extends JpaRepository<GoodsCode, GoodsCodePK>, JpaSpecificationExecutor<GoodsCode> {

    long countByGoodsAndUsedFalse(DemoGoods goods);
}
