/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.event;

import lombok.Data;
import me.jiangcai.goods.trade.Trade;

/**
 * 发货提醒事件
 * 处理时尽量不要发生异常
 *
 * @author CJ
 */
@Data
public class TradeDispatchRemindEvent {
    private final Trade trade;

}
