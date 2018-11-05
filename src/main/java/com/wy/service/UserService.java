package com.wy.service;

import com.wy.dao.UserDAO;
import com.wy.domain.User;
import com.wy.entity.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuanwang on 17/8/15.
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    /**
     * 像user表中插入一条记录
     */
    public CommonResult addUser(User user) {
        int rows = userDAO.insert(user);
        CommonResult commonResult = new CommonResult();
        logger.info("addUser return:{}", rows);
        if(rows > 0){
            commonResult.setCode(0);
            commonResult.setMsg("Success");
            commonResult.setData(user);
        } else {
            commonResult.setCode(1);
            commonResult.setMsg("Fail");
            commonResult.setData(user);
        }
        return commonResult;
    }

}
