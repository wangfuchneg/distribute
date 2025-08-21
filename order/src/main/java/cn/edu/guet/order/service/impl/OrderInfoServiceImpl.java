package cn.edu.guet.order.service.impl;

import cn.edu.guet.order.domain.OrderInfo;
import cn.edu.guet.order.feign.FeignService;
import cn.edu.guet.order.mapper.OrderInfoMapper;
import cn.edu.guet.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liwei
 * @description 针对表【order_info(订单表)】的数据库操作Service实现
 * @createDate 2025-08-18 16:25:30
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private OrderInfoMapper orderInfoMapper;

    @Autowired
    public void setOrderInfoMapper(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    @Override
    @Transactional
    public boolean createOrder(OrderInfo orderInfo) {
        // 添加成功返回1
        if (orderInfoMapper.insert(orderInfo) > 0) {
            return true;
        }
        return false;
    }
}




