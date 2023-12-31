package com.yihang.ultrat.common.utils;

import com.yihang.ultrat.common.domain.dto.RequestInfo;

public class RequestHolder {
    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
