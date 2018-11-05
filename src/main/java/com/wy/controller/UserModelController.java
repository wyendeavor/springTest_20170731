package com.wy.controller;

import com.wy.domain.User;
import com.wy.service.UserService;
import com.wy.utils.JsonFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by yuanwang on 17/8/15.
 */
@Controller
@RequestMapping("/dao")
public class UserModelController {
    Logger logger = LoggerFactory.getLogger(UserModelController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public @ResponseBody Object addUser(@Valid User user, ModelMap map) {
        logger.info("向数据库中添加一个用户:" + user);
//        map.put("data", userService.addUser(user));
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/add02", method = RequestMethod.POST)
    public @ResponseBody String addUser02(@Valid User user, BindingResult bindingResult,
                                          ModelMap map) {
        logger.info("test messageSource:" + messageSource.getMessage("Pattern.user.birthday", new String[]{}, Locale.CHINA));
        if (bindingResult.hasErrors()) {
            return JsonFormatUtil.formatObjectToStr(bindingResult.getAllErrors());
        } else {
            logger.info("bindingResult.getModel():" + bindingResult.getModel());
            logger.info("向数据库中添加一个用户:" + user);
            return JsonFormatUtil.formatObjectToStr(userService.addUser(user));
        }
    }


}
