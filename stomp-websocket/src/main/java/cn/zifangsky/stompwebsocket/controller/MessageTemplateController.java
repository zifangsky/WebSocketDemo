package cn.zifangsky.stompwebsocket.controller;

import cn.zifangsky.stompwebsocket.common.Constants;
import cn.zifangsky.stompwebsocket.common.SpringContextUtils;
import cn.zifangsky.stompwebsocket.model.User;
import cn.zifangsky.stompwebsocket.model.websocket.Greeting;
import cn.zifangsky.stompwebsocket.model.websocket.HelloMessage;
import cn.zifangsky.stompwebsocket.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 测试{@link org.springframework.messaging.simp.SimpMessagingTemplate}类的基本用法
 * @author zifangsky
 * @date 2018/10/10
 * @since 1.0.0
 */
@Controller
@RequestMapping(("/wsTemplate"))
public class MessageTemplateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;

    /**
     * 简单测试SimpMessagingTemplate的用法
     */
    @PostMapping("/greeting")
    @ResponseBody
    public String greeting(@RequestBody Greeting greeting) {
        this.messagingTemplate.convertAndSend("/topic/greeting", new HelloMessage("Hello," + greeting.getName() + "!"));

        return "ok";
    }

    /**
     * 给指定用户发送WebSocket消息
     */
    @PostMapping("/sendToUser")
    @ResponseBody
    public String chat(HttpServletRequest request) {
        String receiver = request.getParameter("receiver");
        String msg = request.getParameter("msg");
        HttpSession session = SpringContextUtils.getSession();
        User loginUser = (User) session.getAttribute(Constants.SESSION_USER);

        HelloMessage resultData = new HelloMessage(MessageFormat.format("{0} say: {1}", loginUser.getUsername(), msg));
        this.sendToUser(loginUser.getUsername(), receiver, "/topic/reply", JsonUtils.toJson(resultData));

        return "ok";
    }

    /**
     * 给指定用户发送消息，并处理接收者不在线的情况
     * @param sender 消息发送者
     * @param receiver 消息接收者
     * @param destination 目的地
     * @param payload 消息正文
     */
    private void sendToUser(String sender, String receiver, String destination, String payload){
        SimpUser simpUser = userRegistry.getUser(receiver);
        Set<SimpSession> simpSessionSet = simpUser.getSessions();

        //如果接收者存在，则发送消息
        if(StringUtils.isNoneBlank(simpUser.getName())){
            this.messagingTemplate.convertAndSendToUser(receiver, destination, payload);
        }
        //否则将消息存储到redis，等用户上线后主动拉取未读消息
        else{
            logger.info("消息接收者{0}还未建立WebSocket连接，");
            //TODO

        }

    }

    @PostMapping("/pullUnreadMessage")
    @ResponseBody
    public Map<String, Object> pullUnreadMessage(String destination){
        Map<String, Object> result = new HashMap<>();

        return result;
    }



}
