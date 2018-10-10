package cn.zifangsky.stompwebsocket.controller;

import cn.zifangsky.stompwebsocket.model.websocket.Greeting;
import cn.zifangsky.stompwebsocket.model.websocket.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试{@link org.springframework.messaging.simp.SimpMessagingTemplate}类的基本用法
 * @author zifangsky
 * @date 2018/10/10
 * @since 1.0.0
 */
@Controller
public class MessageTemplateController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/messagingTemplate")
    @ResponseBody
    public String greeting(@RequestBody Greeting greeting) {
        this.messagingTemplate.convertAndSend("/topic/greeting", new HelloMessage("Hello," + greeting.getName() + "!"));

        return "ok";
    }

}
