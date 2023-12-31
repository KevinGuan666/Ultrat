package com.yihang.frequencycontrol.annotation;

import com.yihang.ultrat.common.FrequencyControlConstants;

import java.lang.annotation.*;

@Repeatable(FrequencyControlContainer.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrequencyControl {
    String strategy() default FrequencyControlConstants.TOTAL_COUNT_WITH_IN_FIX_TIME;

    int windowSize() default 5;

    int period() default 1;

    String prefixKey() default "";
}
