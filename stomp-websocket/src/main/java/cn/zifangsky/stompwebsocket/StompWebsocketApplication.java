package cn.zifangsky.stompwebsocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@MapperScan("cn.zifangsky.stompwebsocket.mapper")
public class StompWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(StompWebsocketApplication.class, args);
	}
}
