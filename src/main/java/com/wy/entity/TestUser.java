package com.wy.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by yuanwang on 17/8/15.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TestUser {

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "real_name")
    private String realName;

    @JsonProperty(value = "birthday")
    private Date birthday;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
