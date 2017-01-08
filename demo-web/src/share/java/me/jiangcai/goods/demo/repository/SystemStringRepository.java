/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.repository;

import me.jiangcai.goods.demo.entity.SystemString;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CJ
 */
public interface SystemStringRepository extends JpaRepository<SystemString, String> {
}
