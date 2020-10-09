package com.jinhe.modules.system.service.impl;


import com.jinhe.modules.system.dao.SysRegionMapper;
import com.jinhe.modules.system.dto.SysRegionDto;
import com.jinhe.modules.system.entity.SysRegion;
import com.jinhe.modules.system.service.ISysRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements ISysRegionService {

    @Resource
    private SysRegionMapper sysRegionMapper;

    //查询行政区列表
    @Override
    public List<SysRegionDto> selectRegionList(String code) {
        List<SysRegionDto> list = sysRegionMapper.selectRegionList(code);
        return list;
    }

    //新增行政区
    @Override
    public Boolean addRegion(SysRegion sysRegion) {
        String parentCode = sysRegion.getParentCode();
        if (null != parentCode && !"".equals(parentCode)) {
            Integer levelInfo = sysRegionMapper.selectLevelInfo(parentCode);
            // 设置为选择行政区的下一级城市
            sysRegion.setLevelInfo(levelInfo + 1);
        } else sysRegion.setLevelInfo(2);
        String id = UUID.randomUUID().toString().replace("-", "");
        sysRegion.setId(id);
        Timestamp time= new Timestamp(System.currentTimeMillis());//获取系统当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(time);
        time = Timestamp.valueOf(timeStr);
        sysRegion.setUpdateTime(time);

         return this.save(sysRegion);
    }

    //更新行政区列表
    @Override
    public Integer updateRegion(SysRegion sysRegion, String id) {
        String code = sysRegionMapper.selectParentCode(id);
        String newCode = sysRegion.getCode();
        Integer flag = sysRegionMapper.updateParentCodeBycode(code,newCode);
        if (flag < 0) return flag;
        Integer flags = sysRegionMapper.updateByRegionId(sysRegion, id);
        return flags;
    }

    //删除行政区
    @Override
    public Integer delRegion(String id) {
        Integer flags = -1;
        Integer count = sysRegionMapper.getItemCount(id);
        if (count == 0) {
            flags = sysRegionMapper.delRegion(id);
        }
        return flags;
    }

    @Override
    public SysRegion regionForCode(String code) {

        SysRegion sysRegion = sysRegionMapper.regionForCode(code);

        return sysRegion;
    }
}
