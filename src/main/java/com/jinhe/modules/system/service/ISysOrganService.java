package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysOrgan;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface ISysOrganService extends IService<SysOrgan> {




    IPage<SysOrganDto> SelectOrgan(Page<SysOrganDto> page, String userId, String organId);

    Integer DeleteOrganByOrganId(String userId, String organId);

    Integer UpdateOranByid(SysOrgan dto);

    Integer insert(SysOrgan organ);

    //根据userID查询
    List<SysOrgan> SelectOrgansByUserId(String userId, Page page);

    List<SysOrgan> GetOrganSubset(List<String> organIds);

    // 刷新机构表 (Sys_Organ表数据初始化使用，只执行一次) 慎用
    void initSysOrganTable();
}
