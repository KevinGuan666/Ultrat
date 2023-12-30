package com.yihang.ultrat.user.cache;

import com.yihang.ultrat.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCache {
    @Autowired
    private UserDao userDao;


}
