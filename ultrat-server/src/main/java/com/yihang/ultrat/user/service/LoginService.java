package com.yihang.ultrat.user.service;

public interface LoginService {
    boolean verify(String token);

    void renewalTokenIfNecessary(String token);

    String login(Long uid);

    Long getValidUid(String token);
}
