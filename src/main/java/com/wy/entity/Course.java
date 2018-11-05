package com.wy.entity;

import java.util.Date;

/**
 * Created by yuanwang on 2017/8/17.
 */
public class Course {

    private Integer id;

    private String name;

    private Date date;

    public Course() {
    }

    public Course(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
