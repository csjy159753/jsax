package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.dto.FileStoreDto;
import com.jinhe.modules.system.entity.FileStore;
import com.jinhe.modules.system.entity.FileStoreType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
public interface IFileStoreService extends IService<FileStore> {
    List<FileStoreDto> upLoadFiles(MultipartFile[] files, List<FileStoreType> listFileStoreTypefilter);

    FileStore ListById(String id);
}
