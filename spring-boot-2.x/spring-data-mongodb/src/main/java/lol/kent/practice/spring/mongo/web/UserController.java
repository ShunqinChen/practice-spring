package lol.kent.practice.spring.mongo.web;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import com.mongodb.bulk.BulkWriteResult;
import java.util.Arrays;
import java.util.List;
import lol.kent.practice.spring.mongo.dao.UserRepository;
import lol.kent.practice.spring.mongo.dto.UserDTO;
import lol.kent.practice.spring.mongo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping
    public void create(@RequestBody UserDTO user) {
        userRepository.save(user.getName(), user.getAge(), user.getMail());
    }

    @GetMapping("/{name}")
    public BasicDBObject get(@PathVariable("name") String name) throws JsonProcessingException {
        return userRepository.get(name);
    }

    /**
     * 批量更新不同的值
     */
    @PutMapping
    public void batchUpdate() {
        BulkOperations ops = mongoTemplate.bulkOps(BulkMode.UNORDERED, User.class);
        List<User> userList = Arrays.asList(
                new User("5daaa7fb6e9ba10074f7400a", "kent", 20, "kentchensq@gmail.com"),
                new User("5daa9877c8959c007493a951", "bob", 24, "bob@gmail.com"),
                new User("5db2830aba39c80073893906", "rose", 26, "rose@gmail.com"));
//        mongoTemplate.insertAll(userList);

        userList.stream().forEach(user -> {
            Query filter = Query.query(where("_id").is(user.getId()));
            Update update = new Update().set("name", user.getName() + "_" + System.currentTimeMillis());
            ops.updateOne(filter, update);
        });

        BulkWriteResult result = ops.execute();
        log.info("result: {}", result.toString());
    }
}
