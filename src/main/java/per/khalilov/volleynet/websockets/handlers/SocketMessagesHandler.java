package per.khalilov.volleynet.websockets.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import per.khalilov.volleynet.websockets.models.Message;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SocketMessagesHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;
    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message mes = mapper.readValue(message.getPayload(), Message.class);

        if (!sessions.containsKey(mes.getPageId())) {
            sessions.put(mes.getPageId(), session);
        }

        for (WebSocketSession ses: sessions.values()) {
            ses.sendMessage(message);
        }

    }
}
