package com.sxmd.utils;

import com.sxmd.exception.SxmdException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Description: 文件工具
 *
 * @author cy
 * @date 2019年08月02日 14:48
 * Version 1.0
 */
@Slf4j
public class FileUtil extends FileUtils {

    private static final int CACHE = 10 * 1024;

    public FileUtil() {
        throw new SxmdException("工具类不能进行实例化");
    }

    /**
     * Description:   删除文件
     * @author cy
     * @param path:
     * @return java.lang.Boolean
     * @date  2019/8/2 16:30
     */
    public static Boolean deleteFile(String path){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        if(!file.isFile()){
            return false;
        }
        return file.delete();
    }

    /**
     * Description:   文件下载
     * @author cy
     * @param filePath: 要下载的文件路径
     * @param dowloadFileName: 下载生成的文件名称
     * @param response:
     * @return void
     * @date  2019/8/2 15:09
     */
    public static void downloadFile(String filePath,String dowloadFileName,HttpServletResponse response) {
        //文件的路径
        File file = new File(filePath);
        if(file.exists() && file.isFile()){
            InputStream ins = null;
            // 获取文件输出IO流
            OutputStream outs = null;
            BufferedInputStream bins = null;
            BufferedOutputStream bouts = null;
            try {
                ins = new FileInputStream(filePath);
                outs = response.getOutputStream();
                // 放到缓冲流里面
                bins = new BufferedInputStream(ins);
                bouts = new BufferedOutputStream(outs);
                response.reset();
                // 指定下载的文件名--设置响应头
                response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(dowloadFileName, "UTF-8"));
                response.setContentType("application/x-download;charset=UTF-8");
                int bytesRead = 0;
                byte[] buffer = new byte[CACHE];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, CACHE)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();
            } catch (IOException e) {
                log.error("文件流出现异常",e);
            }finally {
                if(ins != null){
                    try {
                        ins.close();
                    } catch (IOException e) {
                        log.warn("文件流关闭异常",e);
                    }
                }
                if(bins != null){
                    try {
                        bins.close();
                    } catch (IOException e) {
                        log.warn("文件流关闭异常",e);
                    }
                }
                if(outs != null){
                    try {
                        outs.close();
                    } catch (IOException e) {
                        log.warn("文件流关闭异常",e);
                    }
                }
                if(bouts != null){
                    try {
                        bouts.close();
                    } catch (IOException e) {
                        log.warn("文件流关闭异常",e);
                    }
                }

            }
        }else {
            log.error("下载文件不存在");
        }
    }
}
