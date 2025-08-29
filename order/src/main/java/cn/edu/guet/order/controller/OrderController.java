// OrderController.java 正确代码（推荐字段注入，简洁且不易出错）
package cn.edu.guet.order.controller;

import cn.edu.guet.order.domain.OrderInfo;
import cn.edu.guet.order.mq.Producer;
import cn.edu.guet.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    // 直接字段注入（Spring 会自动初始化）
    @Autowired
    private Producer producer;

    @Autowired
    private OrderInfoService orderInfoService;

    // 下单接口
    @PostMapping("createOrder")
    public Map<String, String> createOrder(@RequestBody OrderInfo orderInfo) {
        try {
            String orderNo = UUID.randomUUID().toString().replace("-", "");
            orderInfo.setId(orderNo);

            boolean isSuccess = producer.send(orderInfo);
            if (isSuccess) {
                return Collections.singletonMap("orderNo", orderNo);
            } else {
                throw new RuntimeException("订单创建失败，库存不足");
            }
        } catch (Exception e) {
            // 打印异常日志，方便排查
            e.printStackTrace();
            throw new RuntimeException("下单失败：" + e.getMessage());
        }
    }
}