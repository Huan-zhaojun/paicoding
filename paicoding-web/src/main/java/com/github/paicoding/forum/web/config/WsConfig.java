package com.github.paicoding.forum.web.config;

import com.github.paicoding.forum.web.front.chat.rest.SimpleChatgptHandler;
import com.github.paicoding.forum.web.hook.interceptor.WsAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * v1.0 基础版本的websocket长连接相关配置
 *
 * @author YiHui
 * @date 2023/6/5
 */
//@Configuration
//@EnableWebSocket
public class WsConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(), "/chatgpt")
                .setAllowedOrigins("*")
                .addInterceptors(new WsAuthInterceptor());
    }

    @Bean
    public WebSocketHandler chatWebSocketHandler() {
        return new SimpleChatgptHandler();
    }
}
