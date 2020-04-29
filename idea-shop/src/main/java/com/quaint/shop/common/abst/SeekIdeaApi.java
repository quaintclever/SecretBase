package com.quaint.shop.common.abst;

/**
 * <p>
 * desc: 定义api
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 * @deprecated see package-info
 */
@Deprecated
public interface SeekIdeaApi<T, R> {

    /**
     * 统一api 执行方法
     * @param param param
     * @return 统一返回封装
     */
    SeekIdeaResult<R> process(T param);

}
