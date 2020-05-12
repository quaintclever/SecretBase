package com.quaint.shop.spike.rpc;

import com.quaint.shop.member.api.MemberInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 11 May 2020
 */
@FeignClient(name = "shop-member-server")
public interface RpcMemberInfoApi extends MemberInfoApi {
}
