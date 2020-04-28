package com.quaint.shop.common.abst;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 基础Service,封装了通用的接口请求逻辑.
 *
 * @author quaint
 */
public abstract class AbstractSeekIdeaService<T, R> implements SeekIdeaApi<T, R> {

    private static Logger log = LoggerFactory.getLogger(AbstractSeekIdeaService.class);

    @Value("${spring.application.name}")
    String serverName;


    public SeekIdeaResult<R> initSeekIdeaResult() {
        return new SeekIdeaResult<>();
    }

    public SeekIdeaResult<R> initSeekIdeaResult(R body) {
        SeekIdeaResult<R> result = initSeekIdeaResult();
        result.setBody(body);
        return result;
    }

    public SeekIdeaResult<R> initSeekIdeaResult(R body, Integer count) {
        SeekIdeaResult<R> result = initSeekIdeaResult();
        result.setBody(body);
        result.setCount(count);
        return result;
    }



    @FunctionalInterface
    public interface CallerService<T, R> {
        /**
         * 调用者自己实现的 服务
         * @param param param
         * @return result
         * @throws Exception e
         */
        SeekIdeaResult<R> doService(T param) throws Exception;
    }

    /**
     * 通用处理业务代码。
     *
     * @param apiPath api path
     * @param param param
     * @param seekIdeaResult return
     * @param callerService 业务处理 流程
     */
    protected void serviceProxy(String apiPath, T param, SeekIdeaResult<R> seekIdeaResult, CallerService<T, R> callerService) {

        apiPath = serverName + apiPath;
        final String paramStr = JSON.toJSONString(param);
        log.info("【接口请求地址】==> [{}],【接口请求参数】==> [{}]", apiPath, paramStr);

        try {
            // 执行 被代理的 process 方法, 返回 对应result
            SeekIdeaResult<R> result = callerService.doService(param);

            // 对 post 返回的 result 复制
            seekIdeaResult.setBody(result.getBody());
            seekIdeaResult.setCount(result.getCount());
            seekIdeaResult.setCode(200);
            seekIdeaResult.setMsg("success");

        } catch (Exception e) {
            seekIdeaResult.setCode(205);
            seekIdeaResult.setMsg(e.getMessage());
            e.printStackTrace();
        }

    }

}