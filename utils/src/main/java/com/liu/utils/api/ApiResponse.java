package com.liu.utils.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liu.utils.meta.ApiResponseCode;

import java.util.List;

/**
 * Restful API 返回JSON结果:
 *
 * { "code":0, "message":"成功","data":T }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {

    /** 系统状态 */
    @JsonProperty("code")
    private Integer code;

    /** 系统状态的i18n的编码 */
    @JsonProperty("message")
    private String message;

    /** 错误内容列表 */
    @JsonProperty("errors")
    private List<ErrorEntity> errors;

    /** 返回结果内容 */
    @JsonProperty("data")
    private T data;

    /**
     * 成功，没数据
     */
    public ApiResponse() {
        this(ApiResponseCode.CODE_0.getCode(),
                ApiResponseCode.CODE_0.getMessage(), null, null);
    }

    /**
     * 成功，有数据
     */
    public ApiResponse(T data) {
        this(ApiResponseCode.CODE_0.getCode(),
                ApiResponseCode.CODE_0.getMessage(), data, null);
    }

    /**
     * 指定code/message
     */
    public ApiResponse(ApiResponseCode apiResponseCode) {
        this(apiResponseCode.getCode(), apiResponseCode.getMessage(), null,
                null);
    }

    /**
     * 指定code + data
     */
    public ApiResponse(ApiResponseCode apiResponseCode, T data) {
        this(apiResponseCode.getCode(), apiResponseCode.getMessage(), data,
                null);
    }

    /**
     * 指定code + errors
     */
    public ApiResponse(ApiResponseCode apiResponseCode,
                       List<ErrorEntity> errors) {
        this(apiResponseCode.getCode(), apiResponseCode.getMessage(), null,
                errors);
    }

    protected ApiResponse(Integer code, String message, T data,
                          List<ErrorEntity> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorEntity> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
