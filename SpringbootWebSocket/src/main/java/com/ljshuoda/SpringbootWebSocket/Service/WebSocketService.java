package com.ljshuoda.SpringbootWebSocket.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WebSocketService extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Map<WebSocketSession, String> sessions = new HashMap<>();

    /**
     * 处理离线分析相关的WebSocket消息
     *
     * @param session 会话对象
     * @param message 消息体
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

        String offlineId = message.getPayload();
        logger.info("offlineId:" + offlineId);

        if (!sessions.containsKey(session)) {
            sessions.put(session, offlineId);
            logger.info("Add new offline analysis client, offlineId: " + offlineId + " !");
        } else {
            String oldOfflineId = sessions.get(session);
            if (!oldOfflineId.equals(offlineId)) {
                sessions.replace(session, offlineId);
                logger.info("Update offlineId for client: " + oldOfflineId + " -> " + offlineId + " .");
            }
        }
        sendMessage(offlineId,offlineId);
    }

    /**
     * 处理Websocket连接关闭事件
     *
     * @param session 会话实例
     * @param status  关闭状态
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        if (sessions.containsKey(session)) {
            sessions.remove(session);
            logger.info("Remove closed websocket, offlineId: " + sessions.get(session) + " .");
        }
    }

    /**
     * 向指定离线ID关联的会话发送消息
     *
     * @param offlineId 离线ID
     * @param message   消息文本
     */
    public void sendMessage(String offlineId, String message) {
        getKeyByValue(sessions, offlineId).ifPresent(session -> {
                    try {
                        session.sendMessage(new TextMessage(message));
                        logger.info("[OfflineId: " + offlineId + "] Offline message content: \"" + message + "\" .");
                    } catch (IOException ex) {
                        logger.error("[OfflineId: " + offlineId + "] Send offline websocket message error:", ex);
                    }
                }
        );
    }

    /**
     * 从 Map 中获取指定 value 对应的首个 key
     * 使用 Optional 封装返回值，查找 value 不存在时返回 Optional.empty
     *
     * @param map Map对象
     * @param value value值
     * @param <K> Map 键类型
     * @param <V> Map 值类型
     * @return 使用 Optional 封装的内容
     */
    private static <K, V> Optional<K> getKeyByValue(Map<K, V> map, V value) {

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return Optional.of(entry.getKey());
            }
        }

        return Optional.empty();
    }
}
