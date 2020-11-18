package com.jinhe.modules.sys.service.impl;

import com.jinhe.modules.sys.entity.Dictionary;
import com.jinhe.modules.sys.dao.DictionaryMapper;
import com.jinhe.modules.sys.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-11-18
 */
@Service
open class DictionaryServiceImpl : ServiceImpl<DictionaryMapper, Dictionary>(), IDictionaryService {

}
