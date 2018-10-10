package cn.zifangsky.stompwebsocket.model.websocket;

/**
 * Hello
 *
 * @author zifangsky
 * @date 2018/9/30
 * @since 1.0.0
 */
public class Greeting {
    private String name;

    public Greeting() {

    }

    public Greeting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
