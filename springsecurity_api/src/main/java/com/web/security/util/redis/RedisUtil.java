package com.web.security.util.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author YanShen.Wu
 * @date 2018/5/11 10:06:05
 */
@Component
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;
    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;
    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zsetOps;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOps;


    //返回值为设置成功的value数
    public Long setSet(String key, String... value) {
        return setOps.add(key, value);
    }

    //返回值为redis中键值为key的value的Set集合
    public Set<String> getSetMembers(String key) {
        return setOps.members(key);
    }

    public Boolean setZSet(String key, String value, double score) {
        return zsetOps.add(key, value, score);
    }

    public Double getZSetScore(String key, String value) {
        return zsetOps.score(key, value);
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除keys
     * @param keys
     * @return
     */
    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(keys);
    }


    /**
     * 向redis存储值（对象或者List）
     * @param key
     * @return
     */
    public void setValue(String key, Object value) {
        valueOps.set(key, value);
    }

    /**
     * 获取Object对象类型
     * @param key
     * @return
     */
    public Object getObject(String key,Class clazz) {
        return JSON.parseObject(valueOps.get(key).toString(),clazz);
    }

    /**
     * 获取list类型
     * @param key
     * @return
     */
    public List getList(String key, Class clazz){
       return JSON.parseArray(valueOps.get(key).toString(),clazz);
    }

    /**
     * 是否存在key
     * @param key
     * @return
     */
    public boolean haskey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 清除所有key
     * @return
     */
    public String flushdb() {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return "ok";
            }
        });
    }
}
