package com.yihang.ultrat.user.service;


import com.yihang.ultrat.user.domain.WebSocketBaseResp;
import io.netty.channel.Channel;

public interface WebSocketService {
    void handleLoginReq(Channel channel);

    void connect(Channel channel);

    void removed(Channel channel);

    void sendToAllOnline(WebSocketBaseResp<?> resp);

    void sendToAllOnline(WebSocketBaseResp<?> resp, Long skipUid);
}
