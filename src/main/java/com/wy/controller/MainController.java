package com.wy.controller;

import com.wy.domain.Person;
import com.wy.domain.PhoneNumberModel;
import com.wy.domain.User;
import com.wy.formatter.PhoneNumber;
import com.wy.utils.JsonFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by yuanwang on 17/7/31.
 */
@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/person/info", method = RequestMethod.GET)
    @ResponseBody
    public String getPersonInfo(HttpServletResponse response) {
        Person person = new Person("zhangsan", 26, "1991-11-22");
        return JsonFormatUtil.formatObjectToStr(person);
    }


    @RequestMapping(value = "/format/self", method = RequestMethod.GET)
    @ResponseBody
    public String testFormater(
            @PhoneNumber @RequestParam("phone_number") PhoneNumberModel phoneNumber,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date") Date date) {
        return new StringBuilder("phoneNumber").
                append(phoneNumber.toString()).
                append(", date:").append(date).toString();
    }

    @RequestMapping(value = "/cookie")
    @ResponseBody
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId,
                                  @RequestHeader("Accept-Language") String acceptLanguage) {
        System.out.println("JSESSIONID:" + sessionId);
        System.out.println("Accept-Language:" + acceptLanguage);
        return "{\"success\":true}";
    }

    @RequestMapping(value = "/person/binding")
    @ResponseBody
    public String handlePerson(Person person) {
        return JsonFormatUtil.formatObjectToStr(person);
    }

    @RequestMapping(value = "/request")
    @ResponseBody
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("request.getCookies():" + request.getCookies());
        System.out.println("request.getPathInfo()" + request.getPathInfo());
        System.out.println("request.getQueryString():" + request.getQueryString());
        LOGGER.info("name:{}", request.getAttribute("name"));
        return JsonFormatUtil.formatObjectToStr(request.getParameterMap());
    }


    @ModelAttribute("person")
    public Person getPerson() {
        Person person = new Person();
        person.setName("张飞");
        return person;
    }

    @RequestMapping("/model1")
    @ResponseBody
    public String handleModel1(@ModelAttribute("person") Person person) {
        person.setAge(22);
        return JsonFormatUtil.formatObjectToStr(person);
    }


    @RequestMapping("/model2")
    @ResponseBody
    public String handlerModel2(ModelMap modelMap) {
        System.out.println("modelMap:" + modelMap);
        return JsonFormatUtil.formatObjectToStr(modelMap);
    }

    @RequestMapping("/valid1")
    @ResponseBody
    public String handleValid1(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonFormatUtil.formatObjectToStr(bindingResult.getAllErrors());
        } else {
            System.out.println("bindingResult.getModel():" + bindingResult.getModel());
            return JsonFormatUtil.formatObjectToStr(bindingResult.getModel().get("user"));
        }
    }

    @RequestMapping("/valid2")
    public String handleValid2(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult
    ) {

        return "validateResult";

    }


}
