package com.yihang.ultrat.user.domain;

import lombok.Data;

@Data
public class WebSocketBaseResp<T> {
    private Integer type;

    private T data;
}
