package cn.zifangsky.samplewebsocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Greeting
 * @author zifangsky
 * @date 2018/9/30
 * @since 1.0.0
 */
@Controller
public class GreetingController {

    @RequestMapping("/reverse")
    public ModelAndView reverse(){
        return new ModelAndView("websocket/reverse");
    }

    @RequestMapping("/echo")
    public ModelAndView echo(){
        return new ModelAndView("websocket/echo");
    }

    @RequestMapping("/echo_sockjs")
    public ModelAndView echoSockJS(){
        return new ModelAndView("websocket/echo_sockjs");
    }

}
