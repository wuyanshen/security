package com.security;

import com.web.security.entity.User;
import com.web.security.service.UserService;
import com.web.security.util.redis.RedisUtil;
import com.web.security.util.redis.ReedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author YanShen.Wu
 * @date 2018/5/11 10:26:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;

   /* @Autowired
    private JedisUtil jedisUtil;*/


    @Autowired
    private ReedisUtil reedisUtil;

   @Autowired
   private UserService userService;

    @Test
    public void test(){
        if(!RedisUtil.hasKey("phone")){
            RedisUtil.setCacheObject("phone","手机",60);
            System.out.println("key已经存储到了redis");
        }else {
            RedisUtil.delete("phone");
            System.out.println("redis中已经存在了key");
        }
    }

    /*@Test
    public void test1(){
        if(!jedisUtil.exists("phone")){
            jedisUtil.set("phone","手机");
            System.out.println("key已经存储到了redis");
        }else {
            jedisUtil.del("phone");
            System.out.println("redis中已经存在了key");
        }
    }*/

    @Test
    public void test2(){
        if(!redisTemplate.hasKey("phone")){
            redisTemplate.opsForValue().set("phone","手机");
            System.out.println("key已经存储到了redis");
        }else {
            redisTemplate.delete("phone");
            System.out.println("redis中已经存在了key");
        }
    }
    @Test
    public void test3(){
        List<User> list = userService.findAllUser(1,3);
        System.out.println(list.get(0).getUsername());
    }

    @Test
    public void test4(){
        User user = userService.findUser("jack");
        System.out.println(user.getUsername());
    }

    @Test
    public void test5(){
        RedisUtil.flushDB();
    }

    @Test
    public void test6(){
        String str = reedisUtil.get("7");
        System.out.println(str);
    }
}
