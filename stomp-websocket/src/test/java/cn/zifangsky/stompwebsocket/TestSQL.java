package cn.zifangsky.stompwebsocket;

import cn.zifangsky.stompwebsocket.mapper.UserMapper;
import cn.zifangsky.stompwebsocket.model.User;
import cn.zifangsky.stompwebsocket.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试基本数据库连接
 * @author zifangsky
 * @date 2018/7/27
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSQL {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testSelect(){
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(user);

		User user2 = userMapper.selectByUsername("zifangsky");
        System.out.println(JsonUtils.toJson(user2));
	}

}
