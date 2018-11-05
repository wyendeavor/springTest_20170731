package com.wy.spring.mvc.formatter;

import com.wy.spring.mvc.converter.PhoneNumberModel;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuanwang on 17/8/3.
 */
public class PhoneNumberFormatter implements Formatter<PhoneNumberModel> {

    //Pattern是线程安全的，作为类的成员域
    private Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

    @Override
    public PhoneNumberModel parse(String text, Locale locale) throws ParseException {
        if (!StringUtils.hasLength(text)) {
            return null;
        }

        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()){
            PhoneNumberModel phoneNumberModel = new PhoneNumberModel();
            phoneNumberModel.setAreaCode(matcher.group(1));
            phoneNumberModel.setPhoneNumber(matcher.group(2));
            return phoneNumberModel;
        } else {
            //③如果不匹配 转换失败
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", text));
        }
    }

    @Override
    public String print(PhoneNumberModel object, Locale locale) {
        if(object == null) {
            return "";
        }

        return new StringBuilder().append(object.getAreaCode()).append("-").append(object.getPhoneNumber()).toString();
    }
}
