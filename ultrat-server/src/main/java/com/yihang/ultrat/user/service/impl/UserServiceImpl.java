package com.yihang.ultrat.user.service.impl;

import com.yihang.ultrat.user.dao.UserDao;
import com.yihang.ultrat.user.service.UserService;
import com.yihang.ultrat.user.vo.response.UserInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfoResp getUserInfo(Long uid) {
        return null;
    }

    @Override
    public void modifyName(Long uid) {

    }
}
