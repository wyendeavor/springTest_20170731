package com.wy.spring.junit.middleware.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.wy.domain.User;
import com.wy.middleware.kafka.producer.KafkaProducer;
import com.wy.spring.junit.SpringBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Description: kafka消息生产者测试
 *
 * @author wangyuan
 * Date: Created at 2018-11-29 09:47
 */
public class KafkaProducerTest extends SpringBaseTest {

    @Resource(name = "kafkaProducer")
    private KafkaProducer kafkaProducer;

    @Test
    public void sendMsg() {
        String topic = "wy_fighting";
        User user = new User();
        user.setId(11);
        user.setName("zhangsan");
        user.setActualAge(28);

        String msg = JSON.toJSONString(user);

        kafkaProducer.sendMsg(topic, msg, false, 0, "wyTest_");
    }


}
