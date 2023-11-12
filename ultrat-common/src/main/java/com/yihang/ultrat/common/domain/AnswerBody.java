package com.yihang.ultrat.common.domain;

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
}
