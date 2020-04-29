package com.quaint.shop.member.spi.aopspi;

import com.quaint.shop.common.abst.SeekIdeaResult;
import com.quaint.shop.common.annotation.CheckLogin;
import com.quaint.shop.common.helper.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
@RestController
@Api(tags = {"test","分类: 测试"})
public class AopTestSpi {


    @ApiOperation("test1")
    @PostMapping("/test1")
    @CheckLogin
    public SeekIdeaResult<String> test1(@RequestBody String param){
        System.out.println("欢迎下面的用户id 登录");
        System.out.println(UserContext.getUserId());
        System.out.println("test1 function start !!!");
        SeekIdeaResult<String> ideaResult = new SeekIdeaResult<>();
        ideaResult.setBody(param);
        ideaResult.setCount(10);
        return ideaResult;
    }

    @ApiOperation("test2")
    @GetMapping("/test2")
    public String test2(){
        int a = 1/0;
        return "test error";
    }

    @ApiOperation("test3")
    @GetMapping("/test3")
    public String test3(){
        throw new RuntimeException("test error");
    }

}
