package cn.edu.guet.item.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.sql.Date;

public class PromoAndItemDTO {
    // 商品表字段
    private String title;        // 商品标题
    private BigDecimal price;    // 商品原价
    private String description;  // 商品描述

    private String imgUrl;       // 商品图片URL
    // 促销表字段
    private String promoName;    // 促销活动名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;      // 促销开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;        // 促销结束时间
    private BigDecimal promoItemPrice; // 促销价
    // 库存表字段
    private Integer stock; // 初始库存数量

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
