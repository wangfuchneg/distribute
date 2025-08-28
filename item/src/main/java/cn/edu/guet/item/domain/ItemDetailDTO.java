package cn.edu.guet.item.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

// 无需泛型，直接定义实体类
public class ItemDetailDTO {
    // 商品基础字段
    private Integer id;
    private String title;
    private BigDecimal price;
    private String description;
    private String imgUrl;

    // 促销关联字段
    private String promoName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
    private BigDecimal promoItemPrice;

    // 库存关联字段
    private Integer stock;
    private Integer sales;


    // 必须添加 getter/setter，Lombok 或手动实现均可
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public void setTitle(String title) {
        this.title = title; // 必须有这行代码，确保值能被设置
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }



    // 其余字段的 getter/setter 同理...
}


