package com.yihang.ultrat.constant.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NormalStatusEnum {
    NORMAL(0, "Normal"),

    Abnormal(1, "Abnormal");

    private final Integer status;

    private final String label;
}
