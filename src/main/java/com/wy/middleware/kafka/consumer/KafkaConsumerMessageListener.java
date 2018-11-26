package com.wy.middleware.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * Description: kafka消费监听器
 *
 * @author wangyuan
 * Date: Created at 2018-11-25 23:25
 */
public class KafkaConsumerMessageListener implements MessageListener<String, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerMessageListener.class);



    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        LOGGER.info("#KafkaConsumerMessageListener.onMessage..receive kafka message, record:{}", record);
    }


}
