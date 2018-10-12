package cn.zifangsky.stompwebsocket.service.impl;

import cn.zifangsky.stompwebsocket.enums.ExpireEnum;
import cn.zifangsky.stompwebsocket.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zifangsky
 * @date 2018/7/30
 * @since 1.0.0
 */
@Service("redisServiceImpl")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        ValueOperations<String, Object> valueOperation = redisTemplate.opsForValue();
        valueOperation.set(key, value);
    }

    @Override
    public void setWithExpire(String key, Object value, long time, TimeUnit timeUnit) {
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(key);
        boundValueOperations.set(value);
        boundValueOperations.expire(time,timeUnit);
    }

    @Override
    public <K> K get(String key) {
        ValueOperations<String, Object> valueOperation = redisTemplate.opsForValue();

        return (K) valueOperation.get(key);
    }

    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public void addToListLeft(String listKey, ExpireEnum expireEnum, Object... values) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //插入数据
        boundValueOperations.leftPushAll(values);
        //设置过期时间
        boundValueOperations.expire(expireEnum.getTime(),expireEnum.getTimeUnit());
    }

    @Override
    public void addToListRight(String listKey, ExpireEnum expireEnum, Object... values) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //插入数据
        boundValueOperations.rightPushAll(values);
        //设置过期时间
        boundValueOperations.expire(expireEnum.getTime(),expireEnum.getTimeUnit());
    }

    @Override
    public List<Object> rangeList(String listKey, long start, long end) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //查询数据
        return boundValueOperations.range(start, end);
    }
}
