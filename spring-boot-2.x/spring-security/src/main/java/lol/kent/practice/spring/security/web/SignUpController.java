package lol.kent.practice.spring.security.web;

import lol.kent.practice.spring.security.user.domain.CustomUser;
import lol.kent.practice.spring.security.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月27日 15:54
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version x.x.x
 */
@RestController
public class SignUpController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/sign-up")
    public CustomUser signUp(@RequestBody Map<String, String> request) {
        String userName = request.get("username");
        String password = request.get("password");

        return this.save(userName, password);
    }

    /**
     * =========Simulate Service Below=============
     */

    @Autowired
    private UserMapper userMapper;

    public CustomUser save(String name, String inputPassword) {
        String password = passwordEncoder.encode(inputPassword);
        CustomUser user = new CustomUser(name, password);
        userMapper.insert(user);
        return user;
    }
}
