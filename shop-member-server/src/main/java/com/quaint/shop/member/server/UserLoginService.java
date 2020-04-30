package com.quaint.shop.member.server;

import com.quaint.shop.member.dto.login.UserLogin;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
public interface UserLoginService {

    /**
     * 用户登录
     * @param param
     * @return
     */
    UserLogin.Result userLogin(UserLogin.Param param);

}
