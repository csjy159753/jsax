package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.dto.FileStoreDto;
import com.jinhe.modules.system.entity.FileStore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-27
 */
@Mapper
public interface FileStoreMapper extends BaseMapper<FileStore> {

    FileStore ListById(String id);
}
