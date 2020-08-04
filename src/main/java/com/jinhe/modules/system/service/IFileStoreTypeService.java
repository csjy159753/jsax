package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.FileStoreType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-06-29
 */
public interface IFileStoreTypeService extends IService<FileStoreType> {
    List<FileStoreType> GetAllFileStoreType();
    FileStoreType getFileStoreTypeById(Integer id);
}
