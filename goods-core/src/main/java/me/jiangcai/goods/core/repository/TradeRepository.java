/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.core.repository;

import me.jiangcai.goods.core.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CJ
 */
public interface TradeRepository extends JpaRepository<Trade, Long> {
}
