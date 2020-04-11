package com.jinhe.modules.system.dto;


import com.baomidou.mybatisplus.activerecord.Model;
import com.jinhe.modules.system.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserDto  {

    private String id;

    /**
     * 登录名
     */

    private String normalizedUsername;

    private String kkAa;


}
