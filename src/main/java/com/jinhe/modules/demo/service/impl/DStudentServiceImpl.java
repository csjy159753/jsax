package com.jinhe.modules.demo.service.impl;

import com.jinhe.modules.demo.entity.DStudent;
import com.jinhe.modules.demo.dao.DStudentMapper;
import com.jinhe.modules.demo.service.IDStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
@Service
public class DStudentServiceImpl extends ServiceImpl<DStudentMapper, DStudent> implements IDStudentService {

}
