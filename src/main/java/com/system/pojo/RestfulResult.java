package com.system.pojo;

/***
 * Restful风格的结果类
 */
public class RestfulResult {

    private Integer code = 0; //状态码 默认为 0
    private Object token = null; //返回SessionId 默认为空
    private Object data = null; // 返回data

    public RestfulResult() {
    }

    public RestfulResult(Integer code, Object token, Object data) {
        this.code = code;
        this.token = token;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
