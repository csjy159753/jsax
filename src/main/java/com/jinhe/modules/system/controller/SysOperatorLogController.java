package com.jinhe.modules.system.controller;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/system/sys-operator-log")
@Transactional(rollbackFor = Exception.class)
public class SysOperatorLogController {

}

