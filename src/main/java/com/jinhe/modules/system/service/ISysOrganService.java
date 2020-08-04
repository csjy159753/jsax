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


    boolean addsysorgan(SysOrganDto sysorg);

    IPage<HashMap<String, Object>> SelectOrgan(Page<SysOrganDto> page, String userId, String organId);

    Integer DeleteOrganByOrganId(String userId, String organId);

    Integer UpdateOranByid(SysOrganDto dto);

    Integer insert(SysOrgan organ);

    //根据userID查询
    List<SysOrgan> SelectOrgansByUserId(String userId, Page page);

    List<SysOrgan> GetOrganSubset(List<String> organIds);
}
