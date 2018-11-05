package com.wy.utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yuanwang on 17/8/1.
 */
public class JsonFormatUtil {

    static Logger logger = LoggerFactory.getLogger(JsonFormatUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 格式化POJO为字符串
     */
    public static <T> String formatObjectToStr(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e){
            logger.error("format json error:", e);
            return "";
        }
    }



}
