package com.wy.spring.mvc.converter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuanwang on 17/8/2.
 */
public class StringToPhoneNumberConverter implements Converter<String, PhoneNumberModel> {

    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");


    @Override
    public PhoneNumberModel convert(String source) {
        if(!StringUtils.hasLength(source)){
            return null;
        }

        Matcher matcher = pattern.matcher(source);
        if(matcher.matches()){
            PhoneNumberModel phoneNumber = new PhoneNumberModel();
            phoneNumber.setAreaCode(matcher.group(1));
            phoneNumber.setPhoneNumber(matcher.group(2));
            return phoneNumber;
        } else {
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，实际格式为[%s]", source));
        }
    }


    @Test
    public void testStringToPhoneNumberConverter(){
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToPhoneNumberConverter());

        String phoneNumberStr = "010-67312832";
        PhoneNumberModel phoneNumberModel = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);

        System.out.println(phoneNumberModel);
        Assert.assertEquals("010", phoneNumberModel.getAreaCode());
    }


    @Test
    public void testOtherConvert(){
        DefaultConversionService conversionService = new DefaultConversionService();
        System.out.println(conversionService.convert("1", Boolean.class));
        System.out.println(conversionService.convert("1,2,3,4", List.class));
    }











}
