package com.jinhe.modules.comm.controller;

import com.jinhe.common.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("FileStreaming")
public class FileStreaming {

    private String dir = "upload";

    /**
     * 上传图片(通用)
     *
     * @param dir
     * @param files
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ApiOperation(value="文件上传", notes="文件上传")
    @ResponseBody
    public ResultUtil upload(String dir, MultipartFile... files) {
        if (dir != null) {
            this.dir = dir;
        }
        for (MultipartFile file: files) {

        }
        return null;
    }


    public String uploadPicture(MultipartFile multipartfile, String id, String folderSecond) throws Exception {
        if (multipartfile == null || id == null || folderSecond == null) {

        }
        if (multipartfile.getSize() > 50 * 1024 * 1024) {

        }

        //设置统一图片后缀名
        String suffixName;

        //获取图片文件名(不带扩展名的文件名)
        String prefixName = getFileNameWithoutEx(multipartfile.getOriginalFilename());

        //获取图片后缀名,判断如果是png的话就不进行格式转换,因为Thumbnails存在转png->jpg图片变红bug
        String suffixNameOrigin = getExtensionName(multipartfile.getOriginalFilename());

        if ("png".equals(suffixNameOrigin)) {
            suffixName = "png";
        } else {
            suffixName = "jpg";
        }

        //图片存储文件夹
        String filePath = "web/src/main/resources/";

        //图片在项目中的地址(项目位置+图片名,带后缀名)
        String contextPath = filePath + prefixName + "." + suffixName;
        //存的项目的中模版图片
        File tempFile = null;
        //上传时从项目中拿到的图片
        File f = null;
        InputStream inputStream = null;
        try {
            //图片在项目中的地址(项目位置+图片名,带后缀名)
            tempFile = new File(contextPath);
            if (!tempFile.exists()) {
                //生成图片文件
                FileUtils.copyInputStreamToFile(multipartfile.getInputStream(), tempFile);
            }

            /*
             * size(width,height) 若图片横比1920小，高比1080小，不变
             * 若图片横比1920小，高比1080大，高缩小到1080，图片比例不变 若图片横比1920大，高比1080小，横缩小到1920，图片比例不变
             * 若图片横比1920大，高比1080大，图片按比例缩小，横为1920或高为1080
             * 图片格式转化为jpg,质量不变
             */
            BufferedImage image = ImageIO.read(multipartfile.getInputStream());
            if (image.getHeight() > 1080 || image.getWidth() > 1920) {
                if (!"png".equals(suffixName)) {
                    Thumbnails.of(contextPath).size(1920, 1080).outputQuality(1f).outputFormat("jpg").toFile(contextPath);
                } else {
                    Thumbnails.of(contextPath).size(1920, 1080).outputQuality(1f).toFile(contextPath);
                }
            } else {
                if (!"png".equals(suffixName)) {
                    Thumbnails.of(contextPath).outputQuality(1f).scale(1f).outputFormat("jpg").toFile(contextPath);
                } else {
                    Thumbnails.of(contextPath).outputQuality(1f).scale(1f).toFile(contextPath);
                }
            }

            //获取压缩后的图片
            f = new File(contextPath);
            inputStream = new FileInputStream(f);

            //设置三级文件夹名
            String folderThird = id + "/";

            //设置OSS上的二级文件目录
            String folderPath = folderSecond + folderThird;

            //设置图片存储在oss上的名字
            String fileName = prefixName + "." + suffixName;

            //上传图片到OSS,返回图书路径

            return null;
        } catch (Exception e) {

        } finally {
            //将临时文件删除
            tempFile.delete();
            f.delete();
            inputStream.close();
        }
        return null;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }


    /**
     * 获取不带扩展名的文件名
     *
     * @param filename 文件
     * @return
     */
    private static String getFileNameWithoutEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }


}
