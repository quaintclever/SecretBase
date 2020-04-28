package com.quaint.shop.common.abst;

/**
 * <p>
 * desc: 基础返回封装
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
public class SeekIdeaResult<R> {

    private R body;
    private Integer count;
    private String msg;
    private Integer code;


    public R getBody() {
        return body;
    }

    public void setBody(R body) {
        this.body = body;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
