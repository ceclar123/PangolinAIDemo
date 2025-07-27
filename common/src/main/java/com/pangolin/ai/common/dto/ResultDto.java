package com.pangolin.ai.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class ResultDto<T> implements Serializable {
    private static final long serialVersionUID = 2359433679527141950L;

    public static final int SUCCESS_CODE = 0;

    private boolean success;
    private int code;
    private String msg;

    private T data;


    public static <T> ResultDto<T> sucOf(T data) {
        return new ResultDto<T>().setSuccess(true).setCode(SUCCESS_CODE).setMsg("ok").setData(data);
    }

    public static <T> ResultDto<T> failedOf(String msg) {
        return new ResultDto<T>().setSuccess(false).setCode(1).setMsg(msg);
    }
}
