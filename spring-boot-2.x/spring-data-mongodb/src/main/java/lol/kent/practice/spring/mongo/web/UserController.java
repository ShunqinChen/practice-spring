package lol.kent.practice.spring.mongo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import lol.kent.practice.spring.mongo.dao.UserRepository;
import lol.kent.practice.spring.mongo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月01日 11:21
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void create(@RequestBody UserDTO user) {
        userRepository.save(user.getName(), user.getAge(), user.getMail());
    }

    @GetMapping("/{name}")
    public BasicDBObject get(@PathVariable("name") String name) throws JsonProcessingException {
        return userRepository.get(name);
    }
}
