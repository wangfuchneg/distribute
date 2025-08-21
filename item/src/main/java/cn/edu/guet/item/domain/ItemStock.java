package cn.edu.guet.item.domain;

import lombok.Data;

/**
 * 商品库存表
 *
 * @TableName item_stock
 */
@Data
public class ItemStock {

    private Integer id;

    private Integer stock;

    private Integer itemId;

    public void setPromoStock(int promoStock) {
    }

    public void setPromoId(Integer promoId) {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


    public Integer getSales() {
        return null;
    }
}