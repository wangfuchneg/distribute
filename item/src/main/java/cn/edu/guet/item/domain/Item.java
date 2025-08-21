package cn.edu.guet.item.domain;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 商品表
 *
 * @TableName item
 */
@Data
public class Item {

    private Integer id;

    private String title;

    private BigDecimal price;
    private Integer sales;

    private String description;



    private String imgUrl;

    public void setInPromo(boolean b) {
    }

    public void setPromoId(Integer promoId) {
    }

    public void setPromoPrice(BigDecimal promoPrice) {
    }
}