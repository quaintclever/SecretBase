package com.quaint.shop.member.server;

import com.quaint.shop.member.dto.login.MemberLogin;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
public interface MemberLoginService {

    /**
     * 用户登录
     * @param param
     * @return
     */
    MemberLogin.Result memberLogin(MemberLogin.Param param);

}
