package com.jinhe.modules.system.service.impl;

import com.jinhe.modules.system.entity.FileStoreType;
import com.jinhe.modules.system.dao.FileStoreTypeMapper;
import com.jinhe.modules.system.service.IFileStoreTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文件存储类型 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class FileStoreTypeServiceImpl extends ServiceImpl<FileStoreTypeMapper, FileStoreType> implements IFileStoreTypeService {

    @Override
    public List<FileStoreType> getAllFileStoreType() {
        return this.list();
    }

    @Override
    public FileStoreType getFileStoreTypeById(Integer id) {
        return this.getById(id);
    }
}
