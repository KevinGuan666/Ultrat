package com.yihang.ultrat.domain;

import com.yihang.ultrat.exception.ErrorEnum;
import lombok.Data;

@Data
public class AnswerBody<T> {
    private Boolean success;

    private Integer errCode;

    private String errMsg;

    private T data;

    public static <T> AnswerBody<T> success() {
        AnswerBody<T> result = new AnswerBody<>();
        result.setData(null);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> AnswerBody<T> success(T data) {
        AnswerBody<T> result = new AnswerBody<>();
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> AnswerBody<T> fail(Integer code, String msg) {
        AnswerBody<T> result = new AnswerBody<>();
        result.setSuccess(false);
        result.setErrCode(code);
        result.setErrMsg(msg);
        return result;
    }

    public static <T> AnswerBody<T> fail(ErrorEnum errorEnum) {
        AnswerBody<T> result = new AnswerBody<>();
        result.setSuccess(false);
        result.setErrCode(errorEnum.getErrorCode());
        result.setErrMsg(errorEnum.getErrorMsg());
        return result;
    }
}
