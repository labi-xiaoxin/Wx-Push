package com.xiaoxin.comm;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回的数据类（链式编程）
 *
 * @Author:wyp
 * @Date:2020/8/24 16:02
 */
@Data
public class ReturnCode {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回提示
     */
    private String message;
    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 构造方法私有，无法创建ReturnCode类
     */
    private ReturnCode() {
    }

    /**
     * 成功
     *
     * @return ReturnCode
     */
    public static ReturnCode success() {
        ReturnCode returnCode = new ReturnCode();
        returnCode.setSuccess(true);
        returnCode.setCode(ReturnStatus.SUCCESS);
        returnCode.setMessage("成功");
        return returnCode;
    }

    /**
     * 失败
     *
     * @return ReturnCode
     */
    public static ReturnCode fail() {
        ReturnCode returnCode = new ReturnCode();
        returnCode.setSuccess(false);
        returnCode.setCode(ReturnStatus.ERROR);
        returnCode.setMessage("失败");
        return returnCode;
    }

    /**
     * 存成功与否
     *
     * @param success true&false
     * @return 调用者
     */
    public ReturnCode success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 存状态码
     *
     * @param code 状态码
     * @return 调用者
     */
    public ReturnCode code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 存消息
     *
     * @param message 消息
     * @return 调用者
     */
    public ReturnCode message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 存数据
     *
     * @param map 数据
     * @return 调用者
     */
    public ReturnCode data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    /**
     * 存数据
     * @return 调用者
     */
    public ReturnCode data(String key,Object value) {
        this.data.put(key,value);
        return this;
    }
}
