package lol.kent.practice.spring.mybatis.web;

import lol.kent.practice.spring.mybatis.mapper.UserMapper;
import lol.kent.practice.spring.mybatis.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月05日 19:04
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public User get() {
        return userMapper.selectByPrimaryKey(6862);
    }
}
