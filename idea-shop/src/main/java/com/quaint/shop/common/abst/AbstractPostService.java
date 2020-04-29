package com.quaint.shop.common.abst;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础api 封装
 * @author quaint
 * @param <T>
 * @param <R>
 * @deprecated see package-info
 */
@Component
@Deprecated
public abstract class AbstractPostService<T, R> extends AbstractSeekIdeaService<T, R> {

    @PostMapping("/")
    public final SeekIdeaResult<R> post(@RequestBody T param, HttpServletRequest request) {
        // request 用于接口调用 日志打印, 传递给 详细业务方法处理
        SeekIdeaResult<R> seekIdeaResult = new SeekIdeaResult<>();
        this.serviceProxy(request.getServletPath(), param, seekIdeaResult, this::process);
        return seekIdeaResult;
    }

}