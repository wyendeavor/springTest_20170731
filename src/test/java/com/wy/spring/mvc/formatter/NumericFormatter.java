package com.wy.spring.mvc.formatter;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.number.CurrencyFormatter;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by yuanwang on 17/8/4.
 */
public class NumericFormatter {

    @Test
    public void testNumberFormat() throws ParseException{
        CurrencyStyleFormatter currencyStyleFormatter = new CurrencyStyleFormatter();

        //保留小数点后几位
        currencyStyleFormatter.setFractionDigits(2);
        //舍入模式（HALF_UP表示四舍五入）
        currencyStyleFormatter.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println(currencyStyleFormatter.parse("$123.23", Locale.US));
        System.out.println(currencyStyleFormatter.print(new BigDecimal("123"), Locale.US));
        System.out.println(currencyStyleFormatter.print(new BigDecimal("123"), Locale.CHINA));
        System.out.println(currencyStyleFormatter.print(new BigDecimal("123"), Locale.JAPAN));
    }

    @Test
    public void testDefaultFormattingConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        CurrencyStyleFormatter currencyStyleFormatter = new CurrencyStyleFormatter();
        currencyStyleFormatter.setFractionDigits(2);
        currencyStyleFormatter.setRoundingMode(RoundingMode.HALF_UP);

        //注册CurrencyStyleFormatter实现
        conversionService.addFormatter(currencyStyleFormatter);

        //设置Locale为US
        LocaleContextHolder.setLocale(Locale.US);
        System.out.println(conversionService.convert(new BigDecimal("1234.128"), String.class));

        //设置Locale为CHINA
        LocaleContextHolder.setLocale(Locale.CHINA);
        System.out.println(conversionService.convert(new BigDecimal("1234.123"), String.class));

        //设置Locale为Japan
        System.out.println(conversionService.convert("￥1234.125", BigDecimal.class));

    }















}
