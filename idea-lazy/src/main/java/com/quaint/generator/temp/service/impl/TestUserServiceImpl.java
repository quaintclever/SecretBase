package com.quaint.generator.temp.service.impl;

import com.quaint.generator.temp.po.TestUserPo;
import com.quaint.generator.temp.mapper.TestUserMapper;
import com.quaint.generator.temp.service.ITestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quaint
 * @since 2020-04-27
 */
@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUserPo> implements ITestUserService {

}
