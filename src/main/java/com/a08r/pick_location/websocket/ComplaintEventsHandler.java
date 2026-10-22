package com.a08r.pick_location.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Keeps the list of connected clients and tells all of them when a complaint
 * changes. Clients only need the notification; they reload the data
 * themselves through the normal REST API.
 */
@Component
public class ComplaintEventsHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        sessions.remove(session);
    }

    /** type: COMPLAINT_CREATED | COMPLAINT_UPDATED | COMPLAINT_FINISHED | COMPLAINT_DELETED */
    public void broadcast(String type, Long complaintId) {
        String json = "{\"type\":\"" + type + "\",\"complaintId\":"
                + (complaintId == null ? "null" : complaintId) + "}";
        TextMessage message = new TextMessage(json);

        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    // a session must not be written to from two threads at once
                    synchronized (session) {
                        session.sendMessage(message);
                    }
                } else {
                    sessions.remove(session);
                }
            } catch (IOException e) {
                sessions.remove(session);
            }
        }
    }
}