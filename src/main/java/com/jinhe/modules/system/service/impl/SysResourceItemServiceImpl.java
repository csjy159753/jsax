package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysResourceItemDto;
import com.jinhe.modules.system.entity.SysResourceItem;
import com.jinhe.modules.system.dao.SysResourceItemMapper;
import com.jinhe.modules.system.service.ISysResourceItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
@Service
public class SysResourceItemServiceImpl extends ServiceImpl<SysResourceItemMapper, SysResourceItem> implements ISysResourceItemService {

    @Autowired
    private SysResourceItemMapper sysResMap;

    @Override
    public IPage<SysResourceItemDto> getListById(Page page, String id) {
        IPage<SysResourceItemDto> listById = sysResMap.getListById(page, id);
        return listById;
    }

    @Override
    @Transactional
    public SysResourceItem add(SysResourceItem sysRes) {
        Integer flags = 0;
        String id = UUID.randomUUID().toString().replace("-", "");
        Timestamp time = new Timestamp(System.currentTimeMillis());//获取系统当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(time);
        time = Timestamp.valueOf(timeStr);
        sysRes.setUpdateTime(time);
        sysRes.setId(id);
        flags = sysResMap.add(sysRes);
        if (flags > 0) return sysResMap.select(sysRes.getId());
        return null;
    }

    @Override
    public Integer modify(SysResourceItem sysRes, String id) {
        Integer flags = 0;
        flags = sysResMap.modify(sysRes, id);
        return flags;
    }
}
