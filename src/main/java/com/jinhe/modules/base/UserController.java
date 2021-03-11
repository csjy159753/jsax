package com.jinhe.modules.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.config.ResultEnum;
import com.jinhe.common.config.SystemType;
import com.jinhe.common.exception.CustomException;
import com.jinhe.modules.sys.dto.UserInfoDTO;
import com.jinhe.modules.sys.service.ISysOrganService;
import com.jinhe.modules.sys.service.ISysUserOrganService;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author rls
 */
public class UserController extends BaseController {
    /**
     * 后续有要求在做 暂时不做处理
     */
//    @ModelAttribute()
//    public void commonUser(HttpServletRequest request, HttpServletResponse response) {
//        verificationUserId();
//    }

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
    protected UserInfoDTO baseUserinfo() {
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

    /**
     * 验证用户是否存在不存在抛出异常
     */
    protected String verificationUserId() {
        String userId = getUserId();
        if (userId == null) {
            throw new CustomException(ResultEnum.VERIFICATION_USER);
        } else {
            return userId;
        }
    }
}
