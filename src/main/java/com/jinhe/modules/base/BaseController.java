package com.jinhe.modules.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.config.SystemType;
import com.jinhe.modules.sys.dto.UserInfoDTO;
import com.jinhe.modules.sys.service.ISysOrganService;
import com.jinhe.modules.sys.service.ISysUserOrganService;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author rls基础控制器
 */
public class BaseController {

    @Autowired(required = false)
    HttpServletRequest request;
    @Autowired
    private ISysUserService iSysUserService;
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ISysUserOrganService iSysUserOrganService;
    @Autowired
    private ISysOrganService iSysOrganService;

    /**
     * 获取用户的id
     *
     * @return
     */
    protected String getUserId() {
        if (request != null && request.getAttribute(SystemType.USER_ID) != null) {
            return request.getAttribute(SystemType.USER_ID).toString();
        }
        return null;
    }

    /**
     * 获取用户的基本信息
     *
     * @return
     */
    protected UserInfoDTO UserInfo() {
        SysUser sysUser = iSysUserService.getById(getUserId());
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setSysUser(sysUser);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", getUserId());
        queryWrapper.select("organ_id");
        List<String> listOrganId = iSysUserOrganService.listObjs(queryWrapper);
        if (listOrganId != null && listOrganId.size() > 0) {
            QueryWrapper queryWrapperOrgan = new QueryWrapper();
            queryWrapperOrgan.in("id", listOrganId);
            List<SysOrgan> listOrgan = iSysOrganService.list(queryWrapperOrgan);
            userInfoDTO.setListOrgan(listOrgan);
        }
        return userInfoDTO;
    }
}
