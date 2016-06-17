package com.pangypeng.weather.bean;

import java.io.Serializable;

/**
 * 作者 pangypeng
 * 时间 2016/6/9 19:45
 * 文件 Weather
 * 描述
 */
public class JsonData implements Serializable {
    /**
     * {
     reason: "successed!",
     result: {},
     error_code: 0
     }
     */
    private int error_code;
    private String reason;
    private Result result;

    public JsonData(){}
    public JsonData(int error_code, String reason, Result result) {
        this.error_code = error_code;
        this.reason = reason;
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
