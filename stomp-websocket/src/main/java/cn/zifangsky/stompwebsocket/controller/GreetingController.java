package cn.zifangsky.stompwebsocket.controller;

import cn.zifangsky.stompwebsocket.model.websocket.Greeting;
import cn.zifangsky.stompwebsocket.model.websocket.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Greeting
 * @author zifangsky
 * @date 2018/9/30
 * @since 1.0.0
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public HelloMessage greeting(Greeting greeting) {
        return new HelloMessage("Hello," + greeting.getName() + "!");
    }
}
