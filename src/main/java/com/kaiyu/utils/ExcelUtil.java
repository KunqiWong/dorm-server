package com.kaiyu.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.WriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;



@Slf4j
public class ExcelUtil {

    public static <T> void export(HttpServletResponse response, String fileName, Class<T> clazz, Collection<T> data){
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                    .inMemory(Boolean.TRUE)
                    .sheet("模板")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("Excel导出出错 : {}",e.getMessage());
        }
    }

    public static <T> void export(HttpServletResponse response, String fileName, Class<T> clazz, Collection<T> data, WriteHandler handler){
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                    .inMemory(Boolean.TRUE)
                    .registerWriteHandler(handler)
                    .sheet("模板")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("Excel导出出错 : {}",e.getMessage());
        }
    }

}
