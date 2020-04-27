package com.quaint.shop.spi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 26 April 2020
 */
@RestController
public class TestController {


    @GetMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

}
