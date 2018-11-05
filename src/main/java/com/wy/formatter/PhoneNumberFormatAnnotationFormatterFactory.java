package com.wy.formatter;

import com.wy.domain.PhoneNumberModel;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yuanwang on 17/8/5.
 */
public class PhoneNumberFormatAnnotationFormatterFactory
        implements AnnotationFormatterFactory<PhoneNumber> {

    private final Set<Class<?>> fieldTypes;

    private final Formatter phoneNumberFormatter;

    public PhoneNumberFormatAnnotationFormatterFactory() {
        Set<Class<?>> typeSet = new HashSet<>();
        typeSet.add(PhoneNumberModel.class);
        this.fieldTypes = typeSet;
        this.phoneNumberFormatter = new PhoneNumberFormatter();
    }


    @Override
    public Set<Class<?>> getFieldTypes() {
        return this.fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(PhoneNumber annotation, Class<?> fieldType) {
        return this.phoneNumberFormatter;
    }

    @Override
    public Parser<?> getParser(PhoneNumber annotation, Class<?> fieldType) {
        return this.phoneNumberFormatter;
    }
}
