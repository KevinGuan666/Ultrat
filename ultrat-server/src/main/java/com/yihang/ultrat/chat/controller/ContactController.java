package com.yihang.ultrat.chat.controller;


import com.yihang.ultrat.constant.utils.RequestHolder;
import com.yihang.ultrat.domain.AnswerBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ultrat/chat")
@Slf4j
public class ContactController {

    @GetMapping("/public/contact/page")
    public AnswerBody getContactPage() {
        Long uid = RequestHolder.get().getUid();
        return AnswerBody.success();
    }
}
