package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResourceDTO> selectSysResourcepage(@Param("userid") String userid);

    SysResourceDTO selectSysResourceOne(String ID);

    List<SysResource> selectsysresTree(Map map);

    List<SysResourceDTO> selectPageAll();

}
