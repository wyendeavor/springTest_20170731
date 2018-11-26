package com.wy.middleware.kafka.producer;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description: kafka消息生产者
 *
 * @author wangyuan
 * Date: Created at 2018-11-26 21:58
 */
@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;


    public Map<String, String> sendMsg(String topic, Object msgObj, boolean usePartition,
                                       int partitionNum, String msgKey) {
        String key = msgKey + "_" + String.valueOf(msgObj.hashCode());
        String msgText = JSON.toJSONString(msgObj);

        ListenableFuture<SendResult<String, String>> resultFu;
        if (usePartition) {
            int partitionIndex = getPartitionIndex(key, partitionNum);
            resultFu = kafkaTemplate.send(topic, partitionIndex, key,
                    msgText);
        } else {
            resultFu = kafkaTemplate.send(topic, key, msgText);
        }

        Map<String, String> res = handleProduceMsgResult(resultFu);
        return res;
    }


    /**
     * 获取partition分区id
     */
    private int getPartitionIndex(String key, int partitionNum) {
        if (StringUtils.isEmpty(key)) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        } else {
            int index = Math.abs(key.hashCode()) % partitionNum;
            return index;
        }
    }

    /**
     * 处理发送msg的结果
     */
    private Map<String, String> handleProduceMsgResult(ListenableFuture<SendResult<String, String>> resultFu) {
        Map<String, String> resultMap = new HashMap<>();

        if (resultFu == null) {
            resultMap.put("code", "0001");
            resultMap.put("msg", "result is null");
            return resultMap;
        }

        try {
            SendResult<String, String> sendResult = resultFu.get();
            Long offsetIndex = sendResult.getRecordMetadata().offset();
            if (offsetIndex != null && offsetIndex > 0) {
                resultMap.put("code", "0000");
                resultMap.put("msg", "send msg success");
                return resultMap;
            }
        } catch (Exception ex) {
            LOGGER.error("kafka send message failed, ex:", ex);
        }

        resultMap.put("code", "0002");
        resultMap.put("msg", "send msg exception");
        return resultMap;
    }


}
