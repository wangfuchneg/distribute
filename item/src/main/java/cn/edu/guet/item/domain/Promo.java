package cn.edu.guet.item.domain;

import java.math.BigDecimal;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName promo
 */
@Data
public class Promo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String promoName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Integer itemId;

    private BigDecimal promoItemPrice;
}