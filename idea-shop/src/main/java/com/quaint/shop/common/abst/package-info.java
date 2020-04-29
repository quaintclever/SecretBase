/**
 * 本包提供了一种封装 api 接口 的思路
 * 本项目主要采用 aop 处理 简单的日志和 登录, 暂时非致力于完善 api 的封装
 *
 * 使用方式:
 ============================================================================================
 @RestController
 @RequestMapping("/member/userLogin")
 @Api(tags = {"手机号密码登录", "分类: 会员登录"})
 public class UserLoginSpi extends AbstractPostService<UserLogin.Param, UserLogin.Result> {

    @Autowired
    UserLoginService userLoginService;

    @Override
    public SeekIdeaResult<UserLogin.Result> process(UserLogin.Param param) {
        return initSeekIdeaResult(userLoginService.userLogin(param));
    }

 }
 ============================================================================================
 *
 *
 *
 */
package com.quaint.shop.common.abst;
