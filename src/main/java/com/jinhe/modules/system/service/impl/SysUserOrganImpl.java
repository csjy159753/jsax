package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.modules.system.dao.SysUserOrganMapper;
import com.jinhe.modules.system.entity.SysUserOrgan;
import com.jinhe.modules.system.service.ISysUserOrganService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class SysUserOrganImpl extends ServiceImpl<SysUserOrganMapper, SysUserOrgan> implements ISysUserOrganService {
    @Resource
    private SysUserOrganMapper sysUserOrganMapper;

    @Override
    public boolean insertUserOrgan(List<SysUserOrgan> list) {
        int insert = sysUserOrganMapper.insertList(list);
        if(insert <0){
            return false;
        }else{
            return true;
        }

    }

    public boolean insert(SysUserOrgan organ) {
        int insert = sysUserOrganMapper.insert(organ);
        if(insert <0){
            return false;
        }else{
            return true;
        }

    }

    public boolean delete(String id) {
        int insert = sysUserOrganMapper.deleteById(id);
        if(insert <0){
            return false;
        }else{
            return true;
        }

    }
    public SysUserOrgan selectById(String id) {
        SysUserOrgan userOrgan = sysUserOrganMapper.selectById(id);
        return userOrgan;
    }

    @Override
    public int deleteByUserId(String userId) {
        int i = sysUserOrganMapper.deleteByUserId(userId);
        return i;
    }

}
