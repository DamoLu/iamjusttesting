package com.plcs.web.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @ProjectName plcs-web
 * @ClassName ResultVO
 * @Description TODO
 * @Author AndyHuang
 * @DATE 2019/6/18 16:57
 */
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -8824585616330663966L;
    /**
     * 返回给客户端的结果
     */
    private T result;

    /**
     * 返回给客户端的消息描述
     */
    private String message;

    /**
     * 返回给客户端的消息代码
     */
    private String code;

    /**
     * 执行成功标志
     */
    private Boolean success;

    public ResultVO() {

    }

    /**
     * ServiceResult的构造函数
     *
     * @param success 操作标志
     * @param message 信息
     */
    public ResultVO(final Boolean success, final String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * ServiceResult的构造函数
     *
     * @param success 操作标志
     * @param message 信息
     * @param result  结果
     */
    public ResultVO(final Boolean success, final String message, final T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    /**
     * 获取返回给客户端的结果
     *
     * @return 返回给客户端的结果
     */
    public T getResult() {
        return result;
    }

    /**
     * 设置返回给客户端的结果
     *
     * @param result 新的结果
     */
    public void setResult(final T result) {
        this.result = result;
    }

    /**
     * 获取返回给客户端的消息
     *
     * @return 返回给客户端的消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置返回给客户端的消息
     *
     * @param message 返回给客户端的消息
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 获取执行成功标志
     *
     * @return Boolean 执行成功标志
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * 获取执行成功标志
     *
     * @return Boolean 执行成功标志
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 设置执行成功标志
     *
     * @param success 新的执行成功标志
     */
    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    /**
     * 获取返回给客户端的消息代码
     *
     * @return String 返回给客户端的消息代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置返回给客户端的消息代码
     *
     * @param code 新的消息代码
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * 服务执行过程发生异常时设置错误信息。
     * <p>该方法会将 success设置为false（服务执行失败）</p>
     *
     * @param code    返回给客户端的消息代码
     * @param message 返回给客户端的消息描述
     */
    public void setError(final String code, final String message) {
        this.code = code;
        this.message = message;
        this.success = false;
    }

    /**
     * 服务执行过程发生异常时设置错误信息。
     * <p>该方法会将 success设置为false（服务执行失败）</p>
     *
     * @param message 返回给客户端的消息描述
     */
    public void setError(final String message) {
        this.code = StringUtils.EMPTY;
        this.message = message;
        this.success = false;
    }

    /**
     * 以JSON形式返回
     *
     * @return toString JSON字符串
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return toJson();
    }

    /**
     * 以JSON形式返回
     *
     * @return toString JSON字符串
     */
    public String toJson() {
        return JSONObject.toJSONString(this);
    }
}
