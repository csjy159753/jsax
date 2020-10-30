package com.jinhe.modules.system.service;

import com.jinhe.modules.system.entity.FileStoreType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文件存储类型 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface IFileStoreTypeService extends IService<FileStoreType> {
    /**
     * 湖获所有文件类型
     * @return
     */
    List<FileStoreType> getAllFileStoreType();

    /**
     * 根据文件类型获取对象
     * @param id
     * @return
     */
    FileStoreType getFileStoreTypeById(Integer id);
}
