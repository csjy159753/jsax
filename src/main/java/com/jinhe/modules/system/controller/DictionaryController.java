package com.jinhe.modules.system.controller;


import com.jinhe.modules.system.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/system/dictionary")
@Transactional(rollbackFor = Exception.class)
public class DictionaryController {
    @Autowired
    private IDictionaryService iDictionaryService;
}
