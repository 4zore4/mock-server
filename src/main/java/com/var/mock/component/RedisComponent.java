package com.var.mock.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisComponent {

    @Autowired
    private JedisPool jedisPool;

    public Boolean hset(String key, String field, String value){
        Jedis jedis = jedisPool.getResource();
        try {
            Long result = jedis.hset(key, field, value);
            return result != 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public String hget(String key, String field){
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hget(key, field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean hdel(String key, String field){
        Jedis jedis = jedisPool.getResource();
        long res = 0;
        try{
            res = jedis.hdel(key, field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res == 1;
    }



}
