package com.quaint.shop.member.server;

import com.quaint.shop.member.dto.info.MemberInfoDto;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 27 April 2020
 */
public interface MemberInfoService {


    /**
     * 根据id 获取用户信息
     * @param id id
     * @return dto
     */
    MemberInfoDto getMemberInfoById(Long id);

}
