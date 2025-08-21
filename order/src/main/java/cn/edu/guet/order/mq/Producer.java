package cn.edu.guet.order.mq;

import cn.edu.guet.order.domain.OrderInfo;
import cn.edu.guet.order.service.OrderInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 事务消息
 */
@Slf4j
@Component
public class Producer {

    @Autowired
    private OrderInfoService orderInfoService;

    private TransactionMQProducer producer = new TransactionMQProducer("producerGroup");

    @PostConstruct
    public void init() {
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object o) {

                // 此处创建订单（为了提高并发，此处先在redis中执行），本案例暂时直接操作mysql

                // 如果本地事务用的是Redis，你的订单数据，最终还是要落地到MySQL中，
                // 那么order微服务即使生产者，也是消费者（负责把Redis中的订单数据异步保存到MySQL中），
                // 同时item微服务中也有一个《消费者》：负责减库存

                // 如果订单创建成功，则把消息发送给Broker（消息中转站）
                if (orderInfoService.createOrder((OrderInfo) o)) {
                    log.info("本地事务执行成功了：订单创建成功");
                    // 返回COMMIT_MESSAGE：半消息可以投递
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }

            /**
             * 消息回查
             * @param msg Check message
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                log.info("检测本地事务");
                // 需要手动写代码去数据库查询，数据是否真的存在，如果存在则返回COMMIT_MESSAGE，消息即可投递
                if (true) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return null;
            }
        });
    }

    public boolean send(OrderInfo orderInfo) {

        Map<String, Integer> map = new HashMap<>();
        map.put("itemId", orderInfo.getItemId() + 5000);
        Message msg;
        try {
            msg = new Message("reduceStock", "reduceStock", String.valueOf(System.nanoTime()),
                    JSON.toJSONString(map).getBytes("UTF-8"));
            // msg：事务消息（会被消费者拿到）
            // orderInfo本地事务数据
            log.info("发送半消息");
            // 让用户体验更好，只要下单成功，马上返回结果（至于减库存、积分变更这些操作，就以异步方式，慢慢去做）
            TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(msg, orderInfo);
            log.info("<事务消息发送结果>{}", transactionSendResult);
            if(transactionSendResult.getLocalTransactionState()==LocalTransactionState.COMMIT_MESSAGE){
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
