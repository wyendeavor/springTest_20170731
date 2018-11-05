package com.wy.controller;

import com.wy.entity.Course;
import com.wy.enums.BusinessExcelType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by yuanwang on 2017/8/17.
 */
@Controller
@RequestMapping("/poi")
public class PoiExcelDownloadController {


    List<Course> documents = Arrays.asList(
            new Course(1, "Spring MVC Xls View", new Date()),
            new Course(2, "Spring MVC Xlsx View", new Date()),
            new Course(3, "Spring MVC XlsxStreaming View", new Date())
    );

    @RequestMapping("/excel/download")
    public String getDocuments(Model model) {
        model.addAttribute("courses", documents);
        model.addAttribute("businessType", BusinessExcelType.CourseList.getCode());
        return "download";
    }


}
