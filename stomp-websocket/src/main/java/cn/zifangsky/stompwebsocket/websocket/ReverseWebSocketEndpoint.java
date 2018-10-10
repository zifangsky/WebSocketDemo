package cn.zifangsky.stompwebsocket.websocket;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * ReverseWebSocketEndpoint
 *
 * @author zifangsky
 * @date 2018/9/30
 * @since 1.0.0
 */
@ServerEndpoint("/reverse")
public class ReverseWebSocketEndpoint {

    @OnMessage
    public void handleMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText("Reversed: " + new StringBuilder(message).reverse());
    }

}
