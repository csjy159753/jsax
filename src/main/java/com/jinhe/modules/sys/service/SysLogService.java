package com.jinhe.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.jinhe.modules.sys.entity.SysLogEntity;
import com.jinhe.common.utils.PageUtils;

import java.util.Map;

/**
 * 系统日志
 */
public interface SysLogService extends IService<SysLogEntity> {

	PageUtils queryPage(Map<String, Object> params);

}
