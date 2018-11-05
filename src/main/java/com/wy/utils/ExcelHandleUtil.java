package com.wy.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CreationHelper;

import java.io.FileOutputStream;

/**
 * Created by yuanwang on 17/8/16.
 */
public class ExcelHandleUtil {

    public static void createExcelForXLS(String outputFileName) {
        HSSFWorkbook wb = new HSSFWorkbook();

        CreationHelper creationHelper = wb.getCreationHelper();

        //create a new sheet
        HSSFSheet s = wb.createSheet("测试HSSF生成xls的Excel文档");
        //declare a row object reference
        HSSFRow r = null;
        //declare a cell object reference
        HSSFCell c = null;
        HSSFCell c2 = null;

        //create 2 cell styles
        HSSFCellStyle cs = wb.createCellStyle();
        HSSFCellStyle cs2 = wb.createCellStyle();

        HSSFDataFormat df = wb.createDataFormat();

        //create 2 fonts objects
        HSSFFont f = wb.createFont();
        HSSFFont f2 = wb.createFont();

        //set font 1 to 12 points type, blue and bold
        f.setFontHeightInPoints((short)12);
        f.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        f.setBold(true);

        //set font 3 to 10 points type, red and bold
        f2.setFontHeightInPoints((short)10);
        f2.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        f2.setBold(true);

        //set cell style and formatting
        cs.setFont(f);
        cs.setDataFormat(df.getFormat("#,##0.0"));

        //set the other cell style and formatting
        cs2.setFont(f2);
        cs2.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
        cs2.setBorderBottom(BorderStyle.THIN);

        //define some rows
        for(int rownum=0; rownum<30; rownum++) {
            r = s.createRow(rownum);
            for(int cellnum=0; cellnum< 10; cellnum = cellnum + 2){
                c=r.createCell(cellnum);
                c2=r.createCell(cellnum +1);

                c.setCellValue((double) rownum + (cellnum) / 10.0);
                c.setCellStyle(cs);
                c2.setCellValue(
                        creationHelper.createRichTextString("hello, 测试呢，" + cellnum)
                );
                c2.setCellStyle(cs2);
            }
        }

        //Save the created HSSFWorkBook
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFileName);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                try{
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

    }
}
