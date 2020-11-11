package com.jinhe.modules.system.service.impl;

import com.jinhe.modules.system.entity.Dictionary;
import com.jinhe.modules.system.dao.DictionaryMapper;
import com.jinhe.modules.system.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

}
