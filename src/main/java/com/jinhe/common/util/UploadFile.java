package com.jinhe.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.UUID;

public class UploadFile {

    public static String[] excelLoad(MultipartFile file){//返回文件路径，原文件名，文件名，文件大小

        String path = "E:\\uploadFile\\";
        //获取文件名，带后缀
        String originalFilename = file.getOriginalFilename();
        // 如果是获取的含有路径的文件名，那么截取掉多余的，只剩下文件名和后缀名
        int index = originalFilename.lastIndexOf("\\");
        if (index > 0) {
            originalFilename = originalFilename.substring(index + 1);
        }
        //获取文件的后缀格式
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        //文件重新命名，防止文件名重复
        String newFileName = UUID.randomUUID()+"."+fileSuffix;

        File dest = new File(path + newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件

        } catch (Exception e) {
            e.printStackTrace();

        }
        String length = null;
        long size = file.getSize();
        if(size / (1024 *1024)  > 1024){
            double size1 = size / (1024 *1024 * 1024);
            size1 = (double) Math.round(size1 * 100) / 100;
            length = size1 +  "G";

        }else if(size / (1024)  > 1024){
            double size1 = size / (1024 *1024);
            size1 = (double) Math.round(size1 * 100) / 100;
            length = size1 + "MB";
        }else if(size >1024 && size < 1024 *1024){
            double size1 = size / 1024;
            size1 = (double) Math.round(size1 * 100) / 100;
            length = size1 + "KB";
        }


        String[] s = new String[5];
        s[0] = path + newFileName;
        s[1] = originalFilename;
        s[2] = newFileName;
        s[3] = length;
        s[4] = fileSuffix;
        return s;
    }
}
