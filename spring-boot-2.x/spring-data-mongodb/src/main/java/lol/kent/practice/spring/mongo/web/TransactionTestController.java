package lol.kent.practice.spring.mongo.web;

import java.util.Arrays;
import lol.kent.practice.spring.mongo.dao.PostRepository;
import lol.kent.practice.spring.mongo.dao.UserRepository;
import lol.kent.practice.spring.mongo.entity.Post;
import lol.kent.practice.spring.mongo.entity.User;
import lol.kent.practice.spring.mongo.event.DemoEvent;
import lol.kent.practice.spring.mongo.event.DemoEventMessage;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月04日 14:01
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("trans")
public class TransactionTestController {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public TransactionTestController(UserRepository userRepository,
            PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public void post() {
        User user = new User(new ObjectId().toHexString(), "Kent", 23, "kentchensq@hotmail.com");
        this.userRepository.save(user);
        DemoEventMessage msg = DemoEventMessage.builder()
                .type("demo")
                .message("他来了")
                .build();
        DemoEvent event = new DemoEvent(this, msg);
        publisher.publishEvent(event);

        if (user.getAge() < 30) {
            throw new IllegalArgumentException("illegal age parameter");
        }

        Post post = new Post();
        post.setTitle("事务测试_" + new DateTime().getMillis());
        post.setUser(new ObjectId(user.getId()));
        post.setCommentUserIds(Arrays.asList(
                new ObjectId("5f4e2d3232024228b1ada556"),
                new ObjectId("5f4f56d7b692e62231f19134")
        ));

        this.postRepository.save(post);
    }
}
