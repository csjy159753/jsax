package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.modules.system.dao.FileStoreTypeMapper;
import com.jinhe.modules.system.entity.FileStoreType;
import com.jinhe.modules.system.service.IFileStoreTypeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-06-29
 */
@Service
@CacheConfig(cacheNames = "FileStoreTypeCache")//抽取缓存的公共配置
public class FileStoreTypeServiceImpl extends ServiceImpl<FileStoreTypeMapper, FileStoreType> implements IFileStoreTypeService {

//    @Cacheable()
    public List<FileStoreType> GetAllFileStoreType() {
        return this.list();
    }

    @Cacheable(key = "#id")
    public FileStoreType getFileStoreTypeById(Integer id) {
        return this.getById(id);
    }
}
