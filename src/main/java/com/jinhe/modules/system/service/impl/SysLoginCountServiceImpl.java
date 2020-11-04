package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.modules.system.entity.SysLoginCount;
import com.jinhe.modules.system.dao.SysLoginCountMapper;
import com.jinhe.modules.system.service.ISysLoginCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录统计 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysLoginCountServiceImpl extends ServiceImpl<SysLoginCountMapper, SysLoginCount> implements ISysLoginCountService {

    @Override
    public void saveInfo(String userId, String userName, int type) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        SysLoginCount sysLoginCount = this.baseMapper.selectOne(queryWrapper);
        if (sysLoginCount != null) {
            sysLoginCount.setCount(sysLoginCount.getCount() + 1);
            sysLoginCount.setUserName(userName);
            switch (type) {
                case 1:
                    sysLoginCount.setAppCount(sysLoginCount.getAppCount() + 1);
                    break;
                case 2:
                    sysLoginCount.setPcCount(sysLoginCount.getPcCount() + 1);
                    break;
                default:
                    break;
            }
            this.baseMapper.updateById(sysLoginCount);
        } else {
            sysLoginCount = new SysLoginCount();
            sysLoginCount.setCount(1);
            sysLoginCount.setUserId(userId);
            sysLoginCount.setUserName(userName);
            switch (type) {
                case 1:
                    sysLoginCount.setAppCount(1);
                    break;
                case 2:
                    sysLoginCount.setPcCount(1);
                    break;
                default:
                    break;
            }
            this.baseMapper.insert(sysLoginCount);
        }

    }
}
