package com.jinhe.modules.system.controller;


import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.ConfigProperty;
import com.jinhe.modules.system.entity.FileStoreType;
import com.jinhe.modules.system.service.IFileStoreService;
import com.jinhe.modules.system.service.IFileStoreTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件存储类 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/system/file-store")
@Api(description = "文件上传接口", tags = "system-file-store")
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
//        if (file == null || file.length == 0) {
//            return ResultUtil.error(ResultEnum.FILE_NOT_FOUND);
//        }
//        List<FileStoreType> listFileStoreTypefilter = new ArrayList<>();
//        for (MultipartFile f : file) {
//            //获取文件名，带后缀
//            String originalFilename = f.getOriginalFilename();
//            //获取文件的后缀格式
//            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
//            f.getContentType();
//            List<FileStoreType> listFileStoreType = iFileStoreTypeService.GetAllFileStoreType();
//            FileStoreType fileStoreType = listFileStoreType.stream().filter(d -> d.getIsUse() != null
//                    && d.getIsUse() == 1
//                    && d.getExt().equals(fileSuffix)).findFirst().get();
//            if (fileStoreType == null) {
//                return ResultUtil.error(ResultEnum.FILE_NOT_FOUND);
//            }
//            listFileStoreTypefilter.add(fileStoreType);
//        }
//        List<FileStoreDTO> fileStoreDtos = fileStoreService.upLoadFiles(file, listFileStoreTypefilter);
//        if (fileStoreDtos == null || fileStoreDtos.isEmpty()) {
//            return ResultUtil.error(ResultEnum.FILE_UPLOAD_ERROR);
//        } else {
//            return ResultUtil.success(fileStoreDtos);
//        }
        return null;
    }
}
