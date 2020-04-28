package com.quaint.shop.member.spi.login;

import com.quaint.shop.common.abst.AbstractJsonParamService;
import com.quaint.shop.common.abst.SeekIdeaResult;
import com.quaint.shop.member.dto.login.UserLogin;
import com.quaint.shop.member.service.UserLoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc: hello spi test
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
@RestController
@RequestMapping("/member/login")
@Api(tags = {"登录测试","分类: 会员登录"})
public class UserLoginSpi extends AbstractJsonParamService<UserLogin.Param,UserLogin.Result> {

    @Autowired
    UserLoginService userLoginService;

    @Override
    public SeekIdeaResult<UserLogin.Result> process(UserLogin.Param param) {
        return initSeekIdeaResult(userLoginService.userLogin(param));
    }
}
