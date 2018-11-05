package com.wy.enums;

/**
 * Created by yuanwang on 2017/8/17.
 */
public enum BusinessExcelType {
    CourseList("课程列表", 1);


    /**
     * 下载Excel的业务类型,用来决定Excel的内容格式
     */
    private String name;

    /**
     * 下载Excel的业务Code
     */
    private Integer code;

    BusinessExcelType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
