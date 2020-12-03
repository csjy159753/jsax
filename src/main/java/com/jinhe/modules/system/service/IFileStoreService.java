package com.jinhe.modules.system.service;

import com.jinhe.modules.system.dto.FileStoreDTO;
import com.jinhe.modules.system.entity.FileStore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.FileStoreType;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件存储类 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface IFileStoreService extends IService<FileStore> {
    /**
     * 批量上传文件
     *
     * @param files
     * @param listFileStoreTypeFilter
     * @return
     */
    List<FileStoreDTO> upLoadFiles(MultipartFile[] files, List<FileStoreType> listFileStoreTypeFilter);

    /**
     * 讲泛型T对象上传数据进行保存数据进行保存
     *
     * @param list
     * @param t
     * @return
     */
    boolean saveFile(List<FileStoreDTO> list, T t, String type);
}
