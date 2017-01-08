/*
 * 版权所有:蒋才
 * (c) Copyright Cai Jiang
 * 2013-2017. All rights reserved.
 */

package me.jiangcai.goods.demo.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jiangcai.goods.Goods;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

import static me.jiangcai.goods.demo.entity.GoodsCode.CodeLength;

/**
 * @author CJ
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCodePK implements Serializable {

    private static final long serialVersionUID = 3288084479526962059L;

    private Long goods;
    @Column(length = CodeLength)
    private String code;

    public GoodsCodePK(Goods goods, String code) {
        this(goods.getId(), code);
    }

}
