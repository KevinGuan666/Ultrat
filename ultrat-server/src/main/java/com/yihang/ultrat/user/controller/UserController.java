package com.yihang.ultrat.user.controller;


import com.yihang.ultrat.common.utils.RequestHolder;
import com.yihang.ultrat.domain.AnswerBody;
import com.yihang.ultrat.user.service.UserService;
import com.yihang.ultrat.user.vo.response.UserInfoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ultrat/user")
public class UserController {
    @Autowired
    private UserService userService;

    public AnswerBody<UserInfoResp> getUserInfo() {
        return AnswerBody.success(userService.getUserInfo(RequestHolder.get().getUid()));
    }

}
