package com.quaint.shop.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quaint.shop.member.po.MemberInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author quaint
 * @since 2020-04-27
 */
@Mapper
public interface MemberInfoMapper extends BaseMapper<MemberInfoPo> {

    /**
     * 通过手机号登录 校验
     * @param phone phone
     * @param pwd pwd
     * @return po
     */
    @Select("select * from member_info where phone = #{phone} and password = #{pwd} limit 1")
    MemberInfoPo loginCheckByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

}
