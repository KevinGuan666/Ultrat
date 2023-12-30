package com.yihang.ultrat.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserActiveStatusEnum {
    ONLINE(1, "online"),

    OFFLINE(2, "offline");

    private final Integer status;

    private final String label;
}
