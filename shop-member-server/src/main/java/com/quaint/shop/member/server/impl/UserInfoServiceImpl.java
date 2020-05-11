package com.quaint.shop.member.server.impl;

import com.quaint.shop.member.dao.UserInfoMapper;
import com.quaint.shop.member.dto.info.MemberInfoDto;
import com.quaint.shop.member.po.MemberInfoPo;
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
    public MemberInfoDto getUserInfoById(Long id) {
        log.info("【 ===> getMemberInfoById <=== 】method start, param:[{}]", id);
        MemberInfoDto result = new MemberInfoDto();
        MemberInfoPo memberInfoPo = userInfoMapper.selectById(id);
        if (memberInfoPo == null){
            throw new RuntimeException("用户信息不存在");
        }
        BeanUtils.copyProperties(memberInfoPo,result);
        log.info("【 ===> getMemberInfoById <=== 】method end, result:[{}]", result);
        return result;
    }
}
