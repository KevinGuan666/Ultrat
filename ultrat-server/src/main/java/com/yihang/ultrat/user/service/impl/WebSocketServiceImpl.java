package com.yihang.ultrat.user.service.impl;

import cn.hutool.json.JSONUtil;
import com.yihang.ultrat.user.domain.WebSocketBaseResp;
import com.yihang.ultrat.user.domain.dto.WSChannelExtraDTO;
import com.yihang.ultrat.user.service.WebSocketService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {
    private static final ConcurrentHashMap<Channel, WSChannelExtraDTO> ONLINE_WS_MAP = new ConcurrentHashMap();

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void handleLoginReq(Channel channel) {

    }

    @Override
    public void connect(Channel channel) {
        ONLINE_WS_MAP.put(channel, new WSChannelExtraDTO());
    }

    @Override
    public void removed(Channel channel) {

    }

    @Override
    public void sendToAllOnline(WebSocketBaseResp<?> resp) {
        sendToAllOnline(resp, null);
    }

    @Override
    public void sendToAllOnline(WebSocketBaseResp<?> resp, Long skipUid) {
        ONLINE_WS_MAP.forEach((channel, dto) -> {
            if (Objects.nonNull(skipUid) && Objects.equals(dto.getUid(), skipUid)) {
                return;
            }
            threadPoolTaskExecutor.execute(() -> sendMsg(channel, resp));
        });
    }

    private void sendMsg(Channel channel, WebSocketBaseResp<?> webSocketBaseResp) {
        channel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(webSocketBaseResp)));
    }
}
