package com.quaint.shop.member.spi.login;

import com.quaint.shop.common.abst.AbstractPostService;
import com.quaint.shop.common.abst.SeekIdeaResult;
import com.quaint.shop.member.dto.login.SendCode;
import com.quaint.shop.member.dto.login.UserLogin;
import com.quaint.shop.member.enums.SendCodeEnum;
import com.quaint.shop.member.helper.SendCodeHelper;
import com.quaint.shop.member.service.UserLoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc: send code
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
@RestController
@RequestMapping("/member/loginSendCode")
@Api(tags = {"登录发送验证码","分类: 会员登录"})
public class LoginSendCodeSpi extends AbstractPostService<SendCode.Param,SendCode.Result> {

    @Autowired
    SendCodeHelper sendCodeHelper;

    @Override
    public SeekIdeaResult<SendCode.Result> process(SendCode.Param param) {
        SendCode.Result result = new SendCode.Result();
        result.setStatus(sendCodeHelper.sendCode(param.getPhone(), SendCodeEnum.LOGIN_TYPE.getValue()));
        return initSeekIdeaResult(result);
    }
}
