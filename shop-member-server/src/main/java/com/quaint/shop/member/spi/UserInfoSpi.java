package com.quaint.shop.member.spi;

import com.quaint.shop.member.api.UserInfoApi;
import com.quaint.shop.member.dto.info.UserInfoDto;
import com.quaint.shop.member.server.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc: 对外服务 提供的接口. 非内部使用
 * </p>
 *
 * @author quaint
 * @since 30 April 2020
 */
@RestController
public class UserInfoSpi implements UserInfoApi {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public UserInfoDto getUserInfoById(@RequestBody Long id){
        return userInfoService.getUserInfoById(id);
    }

}
