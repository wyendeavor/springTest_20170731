package com.wy.extend;

import java.lang.annotation.*;

/**
 * Created by yuanwang on 17/8/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamName {

    /**
     * The name of the request parameter to bind to.
     */
    String value();

}