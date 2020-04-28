package com.quaint.shop.member.service.impl;

import com.quaint.shop.member.dao.UserInfoMapper;
import com.quaint.shop.member.dto.login.SendCode;
import com.quaint.shop.member.dto.login.UserLogin;
import com.quaint.shop.member.po.UserInfoPo;
import com.quaint.shop.member.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
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
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserLogin.Result userLogin(UserLogin.Param param) {
        log.info("【 ===> userLogin <=== 】method start, param:[{}]", param);
        UserLogin.Result result = new UserLogin.Result();

        UserInfoPo userInfoPo = userInfoMapper.loginCheckByPhoneAndPwd(param.getPhone(), param.getPassword());
        if (userInfoPo != null){
            result.setToken("success");
        } else {
            result.setToken("error");
        }

        log.info("【 ===> userLogin <=== 】method end, result:[{}]", result);
        return result;
    }
}
