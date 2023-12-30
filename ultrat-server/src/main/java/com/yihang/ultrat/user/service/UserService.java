package com.yihang.ultrat.user.service;

import com.yihang.ultrat.user.vo.response.UserInfoResp;

public interface UserService {
    UserInfoResp getUserInfo(Long uid);

    void modifyName(Long uid);
}
