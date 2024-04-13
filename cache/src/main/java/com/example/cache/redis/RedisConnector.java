package com.example.cache.redis;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Service
public class RedisConnector
{
    private Jedis jedis;

    @PostConstruct
    public void init() {
        jedis = new Jedis("localhost", 6379);
        String answer = jedis.configSet("maxmemory-policy", "allkeys-lru");
        System.out.println(answer);
    }

    public String put(String username)
    {
        String sessionId = UUID.randomUUID().toString();
        jedis.set(sessionId, username);
        return sessionId;
    }

    public void remove(String sessionId)
    {
        jedis.del(sessionId);
    }

    public boolean isAuth(String sessionId)
    {
        return jedis.exists(sessionId);
    }
}
