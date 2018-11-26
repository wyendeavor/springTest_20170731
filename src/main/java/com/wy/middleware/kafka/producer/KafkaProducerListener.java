package com.wy.middleware.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * Description: kafka生成消息监听器
 *
 * @author wangyuan
 * Date: Created at 2018-11-26 22:31
 */
public class KafkaProducerListener implements ProducerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerListener.class);


    /**
     * Invoked after the successful send of a message (that is, after it has been acknowledged by the broker).
     *
     * @param topic          the destination topic
     * @param partition      the destination partition (could be null)
     * @param key            the key of the outbound message
     * @param value          the payload of the outbound message
     * @param recordMetadata the result of the successful send operation
     */
    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        LOGGER.info("kafka produce msg success, topic:{}, partition:{}, key:{}, value:{}", topic,
                partition, key, value);
    }

    /**
     * Invoked after an attempt to send a message has failed.
     *
     * @param topic     the destination topic
     * @param partition the destination partition (could be null)
     * @param key       the key of the outbound message
     * @param value     the payload of the outbound message
     * @param exception the exception thrown
     */
    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        LOGGER.error("kafka produce msg failed, topic:{}, partition:{}, key:{}, value:{}", topic,
                partition, key, value);
    }

    /**
     * Return true if this listener is interested in success as well as failure.
     *
     * @return true to express interest in successful sends.
     */
    @Override
    public boolean isInterestedInSuccess() {
        return true;
    }
}
