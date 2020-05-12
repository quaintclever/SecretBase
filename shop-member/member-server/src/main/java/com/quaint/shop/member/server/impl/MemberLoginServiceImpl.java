package com.quaint.shop.member.server.impl;

import com.quaint.shop.member.dao.MemberInfoMapper;
import com.quaint.shop.member.dto.login.MemberLogin;
import com.quaint.shop.member.po.MemberInfoPo;
import com.quaint.shop.member.server.MemberLoginService;
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
public class MemberLoginServiceImpl implements MemberLoginService {

    @Autowired
    MemberInfoMapper memberInfoMapper;

    @Override
    public MemberLogin.Result memberLogin(MemberLogin.Param param) {
        log.info("【 ===> memberLogin <=== 】method start, param:[{}]", param);
        MemberLogin.Result result = new MemberLogin.Result();

        MemberInfoPo memberInfoPo = memberInfoMapper.loginCheckByPhoneAndPwd(param.getPhone(), param.getPassword());
        if (memberInfoPo != null){
            result.setToken("token#"+ memberInfoPo.getId());
        } else {
            result.setToken("error");
        }

        log.info("【 ===> memberLogin <=== 】method end, result:[{}]", result);
        return result;
    }
}
