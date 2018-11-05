package com.wy.view;

import com.wy.entity.Course;
import com.wy.enums.BusinessExcelType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanwang on 2017/8/17.
 * <p>
 * 该view用于实现下载旧版Excel文档的功能, 需要在SpringMVC中进行配置
 */
public class XlsView extends AbstractXlsView {

    ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        Integer businessType = (Integer) model.get("businessType");

        if(BusinessExcelType.CourseList.getCode().equals(businessType)){
            handleCourseExcelView(model, workbook, response);
        }

    }


    /**
     * 对特定格式的Excel视图进行处理
     */
    private void handleCourseExcelView(Map<String, Object> model,
                                       Workbook workbook,
                                       HttpServletResponse response) {
        //设置客户端下载时展示的文件名
        response.setHeader("Content-Disposition", "attachment;filename=\"my_excel.xls\"");

        //从model中获取数据信息
        List<Course> courses = (List<Course>) model.get("courses");

        //创建一个sheet
        Sheet sheet = workbook.createSheet("Spring MVC Course List");

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("课程编号");
        row.createCell(1).setCellValue("课程名称");
        row.createCell(2).setCellValue("开课时间");

        int rowNo = 1;

        for(Course course: courses) {
            Row courseRow = sheet.createRow(rowNo++);
            courseRow.createCell(0).setCellValue(course.getId());
            courseRow.createCell(1).setCellValue(course.getName());
            courseRow.createCell(2).setCellValue(sdf.get().format(course.getDate()));
        }
    }

}
