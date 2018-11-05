package com.wy.controller;

import com.wy.entity.TestUser;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanwang on 17/8/8.
 */
@Controller
@RequestMapping("/user")
public class TestUserController {

    //创建日志对象，用于记录日志
    Logger logger = LoggerFactory.getLogger(TestUserController.class);


    /**
     * 设置使用ContentNegotiatingViewResolver来解析视图
     */
    @RequestMapping(value = "/showUserListMix")
    public String showUserListMix(ModelMap modelMap) {
        List<TestUser> testUserList = new ArrayList<>();
        TestUser testUser1 = new TestUser();
        testUser1.setUserName("tom");
        testUser1.setRealName("汤姆");
        testUser1.setBirthday(new DateTime(1980, 1, 1, 0, 0).toDate());

        TestUser testUser2 = new TestUser();
        testUser2.setUserName("john");
        testUser2.setRealName("约翰");
        testUser2.setBirthday(new DateTime(1987, 4, 1, 0, 0).toDate());

        testUserList.add(testUser1);
        testUserList.add(testUser2);

        modelMap.put("testUserList", testUserList);

        return "index";
    }


    /**
     * 显示用户列表
     */
    @RequestMapping(value = "/showUserList")
    public String showUserList(ModelMap modelMap) {
        List<TestUser> testUserList = new ArrayList<>();
        TestUser testUser1 = new TestUser();
        testUser1.setUserName("tom");
        testUser1.setRealName("汤姆");
        testUser1.setBirthday(new DateTime(1980, 1, 1, 0, 0).toDate());

        TestUser testUser2 = new TestUser();
        testUser2.setUserName("john");
        testUser2.setRealName("约翰");
        testUser2.setBirthday(new DateTime(1987, 4, 1, 0, 0).toDate());

        testUserList.add(testUser1);
        testUserList.add(testUser2);

        modelMap.put("testUserList", testUserList);

        return "user/userList";
    }

    /**
     * 使用xml格式来返回用户信息列表
     */
    @RequestMapping(value = "/showUserListByXml")
    public String showUserListInXml(ModelMap modelMap) {
        List<TestUser> testUserList = new ArrayList<>();
        TestUser testUser1 = new TestUser();
        testUser1.setUserName("tom");
        testUser1.setRealName("汤姆");
        testUser1.setBirthday(new DateTime(1980, 1, 1, 0, 0).toDate());

        TestUser testUser2 = new TestUser();
        testUser2.setUserName("john");
        testUser2.setRealName("约翰");
        testUser2.setBirthday(new DateTime(1987, 4, 1, 0, 0).toDate());

        testUserList.add(testUser1);
        testUserList.add(testUser2);

        modelMap.put("testUserList", testUserList);

        return "userListXml";
    }

    @RequestMapping(value = "/showUserListByJson")
    public String showUserListByJson(ModelMap modelMap) {
        List<TestUser> testUserList = new ArrayList<>();
        TestUser testUser1 = new TestUser();
        testUser1.setUserName("tom");
        testUser1.setRealName("汤姆");
        testUser1.setBirthday(new DateTime(1980, 1, 1, 0, 0).toDate());

        TestUser testUser2 = new TestUser();
        testUser2.setUserName("john");
        testUser2.setRealName("约翰");
        testUser2.setBirthday(new DateTime(1987, 4, 1, 0, 0).toDate());

        testUserList.add(testUser1);
        testUserList.add(testUser2);

        modelMap.put("testUserList", testUserList);

        return "userListJson1";
    }

    @RequestMapping(value = "/uploadPage")
    public String updatePage() {
        return "uploadPage";
    }

    @RequestMapping(value = "/upload")
    public String disposeUpload(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        logger.info("用户{}在上传文件", name);
        if (!file.isEmpty()) {
            logger.info("上传文件成功, 原文件名为：" + file.getOriginalFilename());
            file.transferTo(new File("/Users/yuanwang/tmp/" + file.getOriginalFilename()));
            return "redirect:success.html";
        } else {
            logger.info("上传文件失败");
            return "redirect:fail.html";
        }

    }

    @RequestMapping(value = "/success")
    public String uploadSuccess() {
        return "success";
    }


    @RequestMapping(value = "/fail")
    public String uploadFail() {
        return "fail";
    }


}
