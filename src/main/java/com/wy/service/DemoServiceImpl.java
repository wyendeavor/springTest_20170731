package com.wy.service;

import com.wy.dubbotest.api.DemoService;
import com.wy.dubbotest.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuanwang on 2017/8/25.
 *
 * 该Service实现了dubbo服务中的特定接口，供服务消费方调用
 */
@Service("dubboDemoService")
public class DemoServiceImpl implements DemoService {

    Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    private ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Override
    public String sayHello(String s) {
        return "Hello, " + s;
    }

    @Override
    public List<User> getUsers(){
        List<User> dubboUserList = new ArrayList<>();

        try{
            User user01=new User("zhangsan", 26, sdf.get().parse("1991-11-01"));
            User user02 = new User("lisi", 28, sdf.get().parse("1989-12-10"));
            dubboUserList.add(user01);
            dubboUserList.add(user02);
        } catch (Exception e){
            logger.error("getUsers Error:", e);
        }

        return dubboUserList;
    }
}
