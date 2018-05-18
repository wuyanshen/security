package com.web.security.util.redis;

import com.alibaba.fastjson.JSON;
import com.web.security.util.SpringContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 * @author YanShen.Wu
 * @date 2018/5/11 10:06:05
 */
public final class RedisUtil {


    private static RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key   缓存的键值
     * @param value 缓存的值
     * @param cacheSeconds 缓存过期时间 (单位：秒钟) 0为永不过期
     * @return 缓存的对象
     */
    public static  <T> ValueOperations<String, T> setCacheObject(String key, T value,int cacheSeconds) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        if (cacheSeconds != 0) {
            redisTemplate.expire(key, cacheSeconds,TimeUnit.SECONDS);
        }
        return operation;
    }


    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> Object getCacheObject(String key,Class clazz) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return JSON.parseObject(operation.get(key).toString(),clazz);
    }

    /**
     * 获得缓存的String
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> String getCacheString(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        if(operation.get(key)==null){
            return null;
        }
        return operation.get(key).toString();
    }

    /**
     * 根据key删除缓存对象
     *
     * @param key
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection
     */
    public static void deleteObject(Collection collection) {
        redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @param cacheSeconds 缓存过期时间 (单位：秒钟) 0为永不过期
     * @return 缓存的对象
     */
    public static <T> ListOperations<String, T> setCacheList(String key, List<T> dataList,int cacheSeconds) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.leftPush(key, dataList.get(i));
            }
            if (cacheSeconds != 0) {
                redisTemplate.expire(key, cacheSeconds,TimeUnit.SECONDS);
            }
        }
        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public static <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add(listOperation.index(key, i));
        }
        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     * @param cacheSeconds 缓存过期时间 (单位：秒钟) 0为永不过期
     */
    public static <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet,int cacheSeconds) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        if (cacheSeconds != 0) {
            redisTemplate.expire(key, cacheSeconds,TimeUnit.SECONDS);
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public static <T> Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @param cacheSeconds 缓存过期时间 (单位：秒钟) 0为永不过期
     * @return
     */
    public static <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap,int cacheSeconds) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
            if (cacheSeconds != 0) {
                redisTemplate.expire(key, cacheSeconds,TimeUnit.SECONDS);
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public static <T> Map<String, T> getCacheMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }


    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @param cacheSeconds 缓存过期时间 (单位：秒钟) 0为永不过期
     * @return
     */
    public static <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap,int cacheSeconds) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
            if (cacheSeconds != 0) {
                redisTemplate.expire(key, cacheSeconds,TimeUnit.SECONDS);
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public static <T> Map<Integer, T> getCacheIntegerMap(String key) {
        Map<Integer, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }
    /**
     * 是否存在key
     * @param key
     * @return
     */
    public static boolean haskey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取key的有效期
     * @param key
     */
    public long getExpireTime(String key){
        long time = redisTemplate.getExpire(key);
        return time;
    }


    /**
     * 清除所有key
     * @return
     */
    public static Object flushdb() {
        return redisTemplate.execute(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * redis数据库条数
     */
    public static Long dbSize() {
        return (Long)redisTemplate.execute((RedisCallback) c -> c.dbSize());
    }


    /**
     * 获得redis数据库所有的key
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern){
        return (Set<String>)redisTemplate.execute(new RedisCallback() {
            @Nullable
            @Override
            public Set<String> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<String> set = new HashSet<>();
                for(byte[] key:redisConnection.keys(pattern.getBytes())){
                    set.add(getUTF(key));
                }
                return set;
            }
        });
    }

    private static String getUTF(byte[] data){
        try {
            return new String(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 使用客户端向 Redis 服务器发送一个 PING ，如果连接正常就返回一个 PONG ，否则返回一个连接错误。
     * 通常用于测试与服务器的连接是否仍然生效，或者用于测量延迟值
     * @return
     */
    public static String ping() {
        return (String)redisTemplate.execute((RedisCallback)c -> c.ping());
    }
}
