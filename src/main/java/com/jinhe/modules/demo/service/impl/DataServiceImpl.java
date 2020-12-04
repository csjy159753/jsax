package com.jinhe.modules.demo.service.impl;

import com.jinhe.modules.demo.entity.Data;
import com.jinhe.modules.demo.dao.DataMapper;
import com.jinhe.modules.demo.service.IDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-12-04
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements IDataService {

}
