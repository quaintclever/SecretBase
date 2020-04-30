package com.quaint.shop.member.server.impl;

import com.quaint.shop.member.dao.UserInfoMapper;
import com.quaint.shop.member.dto.info.UserInfoDto;
import com.quaint.shop.member.po.UserInfoPo;
import com.quaint.shop.member.server.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDto getUserInfoById(Long id) {
        log.info("【 ===> getUserInfoById <=== 】method start, param:[{}]", id);
        UserInfoDto result = new UserInfoDto();
        UserInfoPo userInfoPo = userInfoMapper.selectById(id);
        if (userInfoPo == null){
            throw new RuntimeException("用户信息不存在");
        }
        BeanUtils.copyProperties(userInfoPo,result);
        log.info("【 ===> getUserInfoById <=== 】method end, result:[{}]", result);
        return result;
    }
}
