package com.yihang.ultrat.user.service.impl;

import com.yihang.ultrat.common.utils.JwtUtils;
import com.yihang.ultrat.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private JwtUtils jwtUtils;

    private static final Integer TOKEN_EXPIRE_DAYS = 5;

    private static final Integer TOKEN_RENEWAL_DAYS = 2;

    @Override
    public boolean verify(String token) {

        return false;
    }

    @Override
    public void renewalTokenIfNecessary(String token) {

    }

    @Override
    public String login(Long uid) {
        return null;
    }

    @Override
    public Long getValidUid(String token) {
        return null;
    }
}
