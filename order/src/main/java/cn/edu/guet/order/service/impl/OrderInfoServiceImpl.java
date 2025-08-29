package cn.edu.guet.order.service.impl;

import cn.edu.guet.order.domain.OrderInfo;
import cn.edu.guet.order.feign.FeignService;
import cn.edu.guet.order.mapper.OrderInfoMapper;
import cn.edu.guet.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private OrderInfoMapper orderInfoMapper;
    private FeignService feignService;

    @Autowired
    public void setOrderInfoMapper(OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
    }

    @Autowired
    public void setFeignService(FeignService feignService) {
        this.feignService = feignService;
    }

    @Override
    @Transactional
    public boolean createOrder(OrderInfo orderInfo) {
        // 前置检查：避免空指针
        if (orderInfo == null) {
            throw new IllegalArgumentException("订单信息不能为空");
        }
        if (orderInfoMapper == null) {
            throw new RuntimeException("OrderInfoMapper 未注入，请检查配置");
        }

        try {
            // 1. 生成唯一订单号
            orderInfo.setId(UUID.randomUUID().toString().replace("-", ""));

            // 2. 计算订单总价（单价 * 数量）
            // 增加非空校验，避免 BigDecimal 运算空指针
            if (orderInfo.getItemPrice() == null) {
                throw new IllegalArgumentException("商品单价不能为空");
            }
            if (orderInfo.getAmount() == null || orderInfo.getAmount() <= 0) {
                throw new IllegalArgumentException("购买数量必须大于0");
            }
            BigDecimal totalPrice = orderInfo.getItemPrice()
                    .multiply(new BigDecimal(orderInfo.getAmount()));
            orderInfo.setOrderPrice(totalPrice);

            // 3. 调用商品服务扣减库存
            String reduceResult = feignService.reduceStock(orderInfo.getItemId());
            if (!"success".equals(reduceResult)) {
                throw new RuntimeException("库存扣减失败：" + reduceResult);
            }

            // 4. 保存订单（优化部分）
            int insertRows = orderInfoMapper.insert(orderInfo);
            // 明确判断插入行数是否为1（正常情况下插入成功应为1）
            if (insertRows != 1) {
                throw new RuntimeException("订单插入数据库失败，受影响行数：" + insertRows);
            }
            return true;

        } catch (IllegalArgumentException e) {
            // 处理参数错误（如空值、无效数量）
            throw new RuntimeException("参数错误：" + e.getMessage());
        } catch (Exception e) {
            // 处理其他异常（如数据库错误、Feign调用失败）
            throw new RuntimeException("创建订单失败：" + e.getMessage());
        }
    }
}
