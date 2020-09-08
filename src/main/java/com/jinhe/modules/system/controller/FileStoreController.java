package com.jinhe.modules.system.controller;


import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.ConfigProperty;
import com.jinhe.modules.system.dto.FileStoreDto;
import com.jinhe.modules.system.entity.FileStore;
import com.jinhe.modules.system.entity.FileStoreType;
import com.jinhe.modules.system.service.IFileStoreService;
import com.jinhe.modules.system.service.IFileStoreTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
@RestController
@CrossOrigin
@RequestMapping("/sys/file-store")
@Api(description = "上传文件", tags = "system-file-store")
public class FileStoreController {

    @Resource
    private IFileStoreService fileStoreService;
    @Resource
    private ConfigProperty configProperty;
    @Autowired
    private IFileStoreTypeService iFileStoreTypeService;

    /**
     * 文件上传
     *
     * @return
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @SysLog(value = "测试注解日志切面uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile... file) {
        if (file == null || file.length == 0) {
            return ResultUtil.error(ResultEnum.FILE_NOT_FOUND);
        }
        List<FileStoreType> listFileStoreTypefilter = new ArrayList<>();
        for (MultipartFile f : file) {
            //获取文件名，带后缀
            String originalFilename = f.getOriginalFilename();
            //获取文件的后缀格式
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            f.getContentType();
            List<FileStoreType> listFileStoreType = iFileStoreTypeService.GetAllFileStoreType();
            FileStoreType fileStoreType = listFileStoreType.stream().filter(d -> d.getIsUse() != null
                    && d.getIsUse() == 1
                    && d.getExt().equals(fileSuffix) && d.getType().equals(f.getContentType())).findFirst().get();
            if (fileStoreType == null) {
                return ResultUtil.error(ResultEnum.FILE_NOT_FOUND);
            }
            listFileStoreTypefilter.add(fileStoreType);
        }
        List<FileStoreDto> fileStoreDtos = fileStoreService.upLoadFiles(file, listFileStoreTypefilter);
        if (fileStoreDtos == null || fileStoreDtos.isEmpty()) {
            return ResultUtil.error(ResultEnum.FILE_UPLOAD_ERROR);
        } else {
            return ResultUtil.success(fileStoreDtos);
        }

    }

    @ApiOperation(value = "文件下载", notes = "文件下载")
    @RequestMapping(value = "/download/file", method = RequestMethod.GET)
    @SysLog(value = "文件下载")
    @CrossOrigin
    public void download(HttpServletResponse response, String id) {
        FileStore fileStore = fileStoreService.getBaseMapper().selectById(id);
        if (fileStore == null) {
            return;
        }
        String path = configProperty.GetAbsolutelyDir() + File.separator + fileStore.getOriginalFile();
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = fileStore.getFileName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            filename = URLEncoder.encode(filename, "UTF8");
            String formatFileName = encodingFileName(filename);
            // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/octet-stream");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }
}

