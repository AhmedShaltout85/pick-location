package com.a08r.pick_location.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ComplaintEventsHandler complaintEventsHandler;

    @Autowired
    public WebSocketConfig(ComplaintEventsHandler complaintEventsHandler) {
        this.complaintEventsHandler = complaintEventsHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // ws://localhost:9999/pick-location/ws/complaints
        // "*" matches the @CrossOrigin(origins = "*") used by the controllers;
        // restrict it to your real web origin in production.
        registry.addHandler(complaintEventsHandler, "/ws/complaints")
                .setAllowedOriginPatterns("*");
    }
}