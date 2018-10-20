package com.lottery.common.util;


import cn.bqmart.common.constant.BusinessCode;
import cn.bqmart.common.exception.BusinessException;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/** Created by wuyujia on 17/2/18. */
public class DownloadUtils {

    public static <T> void excelDownload(HttpServletResponse response, String fileName, Class<T> clazz, List<T> list) throws BusinessException {
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("application/form-data");
        try {
            // 转码中文
            fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // TODO: 2017/4/27 文件扩展名 
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + ".xls\"");
        try {
            // 输出excel
            ExcelUtils.writeToExcel(response.getOutputStream(), clazz, list);
        } catch (Exception e) {
            throw new BusinessException(BusinessCode.Export.NO_RECORD_FIND_CODE, "没有数据可以导出");
        }
    }

    public static <T> void excelDownload(HttpServletResponse response, String fileName, Class<T> clazz, List<T> list, String content) throws BusinessException {
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("application/form-data");
        try {
            // 转码中文
            fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // TODO: 2017/4/27 文件扩展名
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + ".xlsx\"");
        try {
            // 输出excel
            ExcelUtils.writeToExcel(response.getOutputStream(), clazz, list, content);
        } catch (Exception e) {
            throw new BusinessException(BusinessCode.Export.NO_RECORD_FIND_CODE, "没有数据可以导出");
        }
    }
}
