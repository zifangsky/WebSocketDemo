package cn.zifangsky.stompwebsocket;

import cn.zifangsky.stompwebsocket.common.Constants;
import cn.zifangsky.stompwebsocket.enums.ExpireEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试redis的基本用法
 * @author zifangsky
 * @date 2018/7/27
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRedis {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testSelect(){
		BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(Constants.REDIS_UNREAD_MSG_PREFIX + "admin");
//        boundValueOperations.rightPush("a");
//        boundValueOperations.rightPush("b");
//        boundValueOperations.rightPush("c");
        boundValueOperations.leftPushAll("m","n");

		boundValueOperations.expire(ExpireEnum.UNREAD_MSG.getTime(),ExpireEnum.UNREAD_MSG.getTimeUnit());

		List<Object> list = boundValueOperations.range(0, -1);
		list.forEach(System.out::print);
		redisTemplate.delete(Constants.REDIS_UNREAD_MSG_PREFIX + "admin");
	}

}
