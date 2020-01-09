package com.travelone.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtil {

    private static JedisPool jedisPool=null;

    static {
        InputStream in = JedisPoolUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String maxTotal = pro.getProperty("redis.pool.maxTotal");
        String maxIdle = pro.getProperty("redis.pool.maxIdle");
        String host = pro.getProperty("redis.ip");
        String port = pro.getProperty("redis.port");
        String maxWaitMillis = pro.getProperty("redis.pool.maxWaitMillis");

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(Integer.parseInt(maxTotal));
        poolConfig.setMaxIdle(Integer.parseInt(maxIdle));
        poolConfig.setMaxWaitMillis(Long.parseLong(maxWaitMillis));

        jedisPool = new JedisPool(poolConfig,host,Integer.parseInt(port));

    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
