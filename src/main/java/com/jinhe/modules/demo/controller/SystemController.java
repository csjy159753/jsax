package com.jinhe.modules.demo.controller;

import com.jinhe.common.annotation.SysLog;
import com.jinhe.config.ConfigProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统信息类
 *
 * @author
 * @email
 * @date 2019-08-18 20:00:36
 */
@RestController
@CrossOrigin
@Api(tags = {"demo"})
@RequestMapping("/demo/system")
public class SystemController {
    @Autowired
    private ConfigProperty configProperty;

    /**
     * 获取当前发布版本
     *
     * @return
     */
    @ApiOperation(value = "当前版本version", notes = "当前版本version111111111")
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @SysLog(value = "当前版本version")
    public String version() {
        return configProperty.getVersion();
    }

}
