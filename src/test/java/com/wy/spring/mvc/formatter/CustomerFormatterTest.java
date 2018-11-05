package com.wy.spring.mvc.formatter;

import com.wy.spring.mvc.converter.PhoneNumberModel;
import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 * Created by yuanwang on 17/8/3.
 */
public class CustomerFormatterTest {

    @Test
    public void testFormatter(){
        DefaultFormattingConversionService formattingConversionService = new DefaultFormattingConversionService();
        //添加指定PhoneNumberModel转换的Formatter
        formattingConversionService.addFormatter(new PhoneNumberFormatter());

        PhoneNumberModel phoneNumber = new PhoneNumberModel("010", "12345678");

        //将对象转换为String
        System.out.println(formattingConversionService.convert(phoneNumber, String.class));

        //将String转换为对象
        System.out.println(formattingConversionService.convert("010-87654321", PhoneNumberModel.class));

    }

}
