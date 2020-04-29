package com.quaint.shop.member.service;

import com.quaint.shop.member.dto.info.UserInfoDto;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 27 April 2020
 */
public interface UserInfoService {


    /**
     * 根据id 获取用户信息
     * @param id id
     * @return dto
     */
    UserInfoDto getUserInfoById(Long id);

}
