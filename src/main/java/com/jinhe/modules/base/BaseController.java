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
 * @author rls基础控制器
 */
public class BaseController {
    /**
     *  后续有要求在做 暂时不做处理
     */
//    @ModelAttribute
//    public void common(HttpServletRequest request, HttpServletResponse response) {
//
//    }
}
