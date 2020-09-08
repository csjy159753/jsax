package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.jinhe.common.util.ImageUtil;
import com.jinhe.common.util.StringUtils;
import com.jinhe.config.ConfigProperty;
import com.jinhe.modules.system.dao.FileStoreMapper;
import com.jinhe.modules.system.dto.FileStoreDto;
import com.jinhe.modules.system.entity.FileStore;
import com.jinhe.modules.system.entity.FileStoreType;
import com.jinhe.modules.system.service.IFileStoreService;
import com.jinhe.modules.system.service.IFileStoreTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
@Service
public class FileStoreServiceImpl extends ServiceImpl<FileStoreMapper, FileStore> implements IFileStoreService {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ConfigProperty configProperty;
    @Resource
    private FileStoreMapper fileStoreMapper;
    @Resource
    private IFileStoreTypeService iFileStoreTypeService;

    @Override
    public List<FileStoreDto> upLoadFiles(MultipartFile[] files, List<FileStoreType> listFileStoreTypefilter) {

        List<FileStoreDto> list = new ArrayList<>();
        for (MultipartFile file : files) {
            double size = file.getSize();
            String length = null;//文件大小
            if (size / (1024 * 1024) > 1024) {
                double size1 = size / (1024 * 1024 * 1024);
                size1 = (double) Math.round(size1 * 100) / 100;
                length = size1 + "G";

            } else if (size / (1024) > 1024) {
                double size1 = size / (1024 * 1024);
                size1 = (double) Math.round(size1 * 100) / 100;
                length = size1 + "MB";
            } else if (size > 1024 && size < 1024 * 1024) {
                double size1 = size / 1024;
                size1 = (double) Math.round(size1 * 100) / 100;
                length = size1 + "KB";
            }

            //获取文件名，带后缀
            String originalFilename = file.getOriginalFilename();
            //获取文件的后缀格式
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            //文件重新命名，防止文件名重复
            String newFileName = StringUtils.getGUID() + "." + fileSuffix;

            //该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程
            String dirPath = configProperty.GetAbsolutelyUpload();
            //获取当前日期和文件类型作为文件子路径
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datePath = sdf.format(date);

            FileStoreType fileStoreType = listFileStoreTypefilter.stream().filter(d -> d.getExt().equals("." + fileSuffix) && d.getType().equals(file.getContentType())).findFirst().get();

            //原文件路径
            String ogiginalPath = File.separator + datePath + File.separator + fileStoreType.getId();
            //修正后的路径
            String modifiedPath = File.separator + datePath + File.separator + fileStoreType.getId() + File.separator + "Modified";
            ;
            //小图路径
            String lowPath = File.separator + datePath + File.separator + fileStoreType.getId() + File.separator + "Low";
            //缩略图路径
            String thumbPath = File.separator + datePath + File.separator + fileStoreType.getId() + File.separator + "Thumb";

            File ogiginalFile = new File(dirPath + ogiginalPath + File.separator + newFileName);//完整目录
            File modifiedFile = new File(dirPath + modifiedPath + File.separator + newFileName);//修正后的目录
            File lowFile = new File(dirPath + lowPath + File.separator + newFileName);
            File thumbFile = new File(dirPath + thumbPath + File.separator + newFileName);
            //若文件路径不存在则创建该路径
            if (!ogiginalFile.getParentFile().exists()) {
                ogiginalFile.getParentFile().mkdirs();
            }
            if (!modifiedFile.getParentFile().exists()) {
                modifiedFile.getParentFile().mkdirs();
            }
            if (!lowFile.getParentFile().exists()) {
                lowFile.getParentFile().mkdirs();
            }
            if (!thumbFile.getParentFile().exists()) {
                thumbFile.getParentFile().mkdirs();
            }
            //获取文件完整存放路径
            try {
                //保存文件到指定的路径中
                file.transferTo(ogiginalFile);

                //文件信息录入数据库
                FileStore fileStore = new FileStore();

                String fileId = UUID.randomUUID().toString().replace("-", "");
                fileStore.setId(fileId);
                fileStore.setFileName(originalFilename);//文件名
                fileStore.setFileId(fileId);
                fileStore.setOriginalFile(configProperty.getUpload() + ogiginalPath + File.separator + newFileName);//文件路径
                fileStore.setMimeTypeExt(fileSuffix);
                fileStore.setMimeTypeType(fileStoreType.getType());


                FileStoreDto fileStoreDto = new FileStoreDto();
                fileStoreDto.setFileId(fileId);
                fileStoreDto.setFileName(originalFilename);
                fileStoreDto.setMimeTypeExt(fileSuffix);
                fileStoreDto.setMimeTypeType(fileStoreType.getType());
                fileStoreDto.setPath(configProperty.getUpload() + ogiginalPath + File.separator + newFileName);
                fileStoreDto.setSize(length);


                //校验图片格式，如为图片类型则根据图片大小判断是否压缩
                List<String> imageType = Lists.newArrayList("jpg", "jpeg", "png", "bmp", "gif");
                if (imageType.contains(fileSuffix)) {
                    try {
                        imageCompress(newFileName, dirPath, ogiginalPath, modifiedPath, lowPath, thumbPath, ogiginalFile, fileStore, fileStoreDto);
                    } catch (Exception ex) {
                        logger.error("图片转换失败", ex);
                    }

                }
                fileStoreMapper.insert(fileStore);
                list.add(fileStoreDto);
            } catch (IOException e) {
            }
        }

        return list;
    }

    /**
     * 图片压缩处理方法
     *
     * @param newFileName
     * @param dirPath
     * @param ogiginalPath
     * @param modifiedPath
     * @param lowPath
     * @param thumbPath
     * @param ogiginalFile
     * @param fileStore
     * @param fileStoreDto
     * @throws IOException
     */
    private void imageCompress(String newFileName, String dirPath, String ogiginalPath, String modifiedPath, String lowPath, String thumbPath, File ogiginalFile, FileStore fileStore, FileStoreDto fileStoreDto) throws IOException {
        File watermark = null;
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(ogiginalFile));
        try {
            if (configProperty.getWatermarkEnabled() == true) {
                watermark = new File(configProperty.GetAbsolutelyWatermarkPath());
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        int imglength = sourceImg.getHeight();
        if (sourceImg.getWidth() > sourceImg.getHeight()) {
            imglength = sourceImg.getWidth();
        }
        //判断图片最大高度或者宽度 去最大值和设置的压缩率做比值进行压缩
        if (imglength > configProperty.getImgCompressionModified()) {
            double d = configProperty.getImgCompressionModified() / imglength;
            if (watermark != null && configProperty.getWatermarkEnabled()) {
                ImageUtil.generateThumbnail2DirectoryWatermark(d, watermark, dirPath + modifiedPath, dirPath + ogiginalPath + File.separator + newFileName);
            } else {
                ImageUtil.generateThumbnail2Directory(d, dirPath + modifiedPath, dirPath + ogiginalPath + File.separator + newFileName);
            }
        } else {
            if (watermark != null && configProperty.getWatermarkEnabled()) {
                ImageUtil.generateThumbnail2DirectoryWatermark(1, watermark, dirPath + modifiedPath, dirPath + ogiginalPath + File.separator + newFileName);
            } else {
                ImageUtil.generateThumbnail2Directory(1, dirPath + modifiedPath, dirPath + ogiginalPath + File.separator + newFileName);
            }

        }
        if (imglength > configProperty.getImgCompressionLowFile()) {
            double d = configProperty.getImgCompressionLowFile() / imglength;
            if (watermark != null && configProperty.getWatermarkEnabled()) {
                ImageUtil.generateThumbnail2DirectoryWatermark(d, watermark, dirPath + lowPath, dirPath + ogiginalPath + File.separator + newFileName);
            } else {
                ImageUtil.generateThumbnail2Directory(d, dirPath + lowPath, dirPath + ogiginalPath + File.separator + newFileName);
            }
        } else {
            if (watermark != null && configProperty.getWatermarkEnabled()) {
                ImageUtil.generateThumbnail2DirectoryWatermark(1, watermark, dirPath + lowPath, dirPath + ogiginalPath + File.separator + newFileName);
            } else {
                ImageUtil.generateThumbnail2Directory(1, dirPath + lowPath, dirPath + ogiginalPath + File.separator + newFileName);
            }
        }
        if (imglength > configProperty.getImgCompressionThumb()) {
            double d = configProperty.getImgCompressionThumb() / imglength;
            if (watermark != null && configProperty.getWatermarkEnabled()) {
                ImageUtil.generateThumbnail2DirectoryWatermark(d, watermark, dirPath + thumbPath, dirPath + ogiginalPath + File.separator + newFileName);
            } else {
                ImageUtil.generateThumbnail2Directory(d, dirPath + thumbPath, dirPath + ogiginalPath + File.separator + newFileName);
            }

        } else {
            if (watermark != null && configProperty.getWatermarkEnabled()) {
                ImageUtil.generateThumbnail2DirectoryWatermark(1, watermark, dirPath + thumbPath, dirPath + ogiginalPath + File.separator + newFileName);
            } else {
                ImageUtil.generateThumbnail2Directory(1, dirPath + thumbPath, dirPath + ogiginalPath + File.separator + newFileName);
            }

        }
        fileStore.setModifiedImgae(configProperty.getUpload() + modifiedPath + File.separator + newFileName);
        fileStore.setLowImgae(configProperty.getUpload() + lowPath + File.separator + newFileName);
        fileStore.setThumbImgae(configProperty.getUpload() + thumbPath + File.separator + newFileName);
        fileStoreDto.setPath(configProperty.getUpload() + modifiedPath + File.separator + newFileName);
    }
}
