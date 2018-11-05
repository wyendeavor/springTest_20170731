package com.wy.spring.mvc.orm;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by yuanwang on 17/8/14.
 */
public class DatabaseSourceTest {

    @Test
    public void dataSourceConfigTest() throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml");
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement("CREATE TABLE user03 (id int(4) NOT NULL AUTO_INCREMENT, " +
                "name char(20) NOT NULL, age int(4) DEFAULT NULL COMMENT '用户年龄', PRIMARY KEY (id));");
        statement.execute();
        Thread.sleep(1000);
        System.out.println("execute success");
    }

}
