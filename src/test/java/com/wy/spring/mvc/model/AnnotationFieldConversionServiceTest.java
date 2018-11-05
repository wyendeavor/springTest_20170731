package com.wy.spring.mvc.model;

import com.wy.spring.mvc.annotation.PhoneNumberFormatAnnotationFormatterFactory;
import com.wy.spring.mvc.converter.PhoneNumberModel;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.TypeConverter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by yuanwang on 17/8/4.
 */
public class AnnotationFieldConversionServiceTest {

    @Test
    public void testAnnotationFieldService() throws NoSuchFieldException{
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        FormatterModel model = new FormatterModel();
        model.setTotalCount(5000);
        model.setDiscount(0.51);
        model.setSumMoney(10000.13);
        model.setRegisterDate(new Date());
        model.setOrderDate(new DateTime(2015, 11, 22, 5, 30, 30).toDate());

        TypeDescriptor fieldDescriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("totalCount"));

        TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);

        //将带有注解的字段类型convert为字符串类型
        System.out.println(conversionService.convert(model.getTotalCount(), fieldDescriptor, stringDescriptor));

        //将字符串类型转换为带有注解的Field类型
        System.out.println(conversionService.convert("10,100", stringDescriptor, fieldDescriptor));

    }

    @Test
    public void testSelfDefineAnnotationFileService() throws NoSuchFieldException{
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //注册自定义的注解解析器
        conversionService.addFormatterForFieldAnnotation(new PhoneNumberFormatAnnotationFormatterFactory());

        FormatterModel formatterModel = new FormatterModel();

        TypeDescriptor phoneNumberDescriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("phoneNumberModel"));
        TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);

        System.out.println(conversionService.convert("010-1234567", stringDescriptor, phoneNumberDescriptor));

        formatterModel.setPhoneNumberModel(new PhoneNumberModel("111", "1231234"));
        System.out.println(conversionService.convert(formatterModel.getPhoneNumberModel(), phoneNumberDescriptor, stringDescriptor));

    }

}



