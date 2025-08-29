package cn.edu.guet.order.service;

import cn.edu.guet.order.domain.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liwei
* @description 针对表【order_info(订单表)】的数据库操作Service
* @createDate 2025-08-18 16:25:31
*/
public interface OrderInfoService{

    boolean createOrder(OrderInfo orderInfo);

}
