package com.wy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by yuanwang on 17/8/16.
 */
@Controller
@RequestMapping(value = "/download")
public class FileDownloadController {
    Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response) throws IOException {
        File downloadFile = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        logger.info("Thread.currentThread().getContextClassLoader().getResource(\"/\").getPath:" + classLoader.getResource("/").getPath());
        downloadFile = new File(classLoader.getResource("excel/poi_test01.xls").getFile());

        //如果文件不存在，返回文本错误信息
        if(!downloadFile.exists()){
            String errorMsg = "对不起，您当前下载的文件不存在.";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMsg.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());
        if(mimeType == null){
            logger.info("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        logger.info("download return mimetype:" + mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", downloadFile.getName()));

        InputStream inputStream = new BufferedInputStream(new FileInputStream(downloadFile));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }



}
