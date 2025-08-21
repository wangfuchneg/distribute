package cn.edu.guet.order.controller;

import cn.edu.guet.order.domain.OrderInfo;
import cn.edu.guet.order.feign.FeignService;
import cn.edu.guet.order.mq.Producer;
import cn.edu.guet.order.service.OrderInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private Producer producer;

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    private OrderInfoService orderInfoService;

    @Autowired
    public void setOrderInfoService(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @PostMapping("createOrder")
    public String createOrder(@RequestBody OrderInfo orderInfo) {
        if (producer.send(orderInfo)) {
            return "下单成功！";
        }
        return "下单失败！";
    }
}
