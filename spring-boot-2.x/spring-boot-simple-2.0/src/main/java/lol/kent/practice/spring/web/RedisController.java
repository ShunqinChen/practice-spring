package lol.kent.practice.spring.web;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述: Redis特性相关练习
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2022年06月23日 14:47
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("redis")
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    /**
     * 测试sessionCallback的基础用法 记得加入 multi() 否则会出现异常
     */
    @GetMapping("sessionCallback")
    public void sessionCallbackTest() {

        var key = "scb:list";
        var data = Sets.newHashSet("a", "b");
        redisTemplate.execute(new SessionCallback() {

            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.delete(key);
                redisOperations.opsForList().leftPushAll(key, data);
                return redisOperations.exec();
            }
        });

    }
}
