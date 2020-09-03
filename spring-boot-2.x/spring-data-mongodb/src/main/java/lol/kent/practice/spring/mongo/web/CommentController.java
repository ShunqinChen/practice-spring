package lol.kent.practice.spring.mongo.web;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import lol.kent.practice.spring.mongo.dao.CommentRepository;
import lol.kent.practice.spring.mongo.dto.CommentDTO;
import lol.kent.practice.spring.mongo.entity.Comment;
import lol.kent.practice.spring.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;
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
 * Copyright: Copyright (c) 2020年09月03日 10:18
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public void create(@RequestBody CommentDTO params) {

        List<User> favorUsers = CollectionUtils.isEmpty(params.getFavorUsers()) ? Collections.emptyList()
                : params.getFavorUsers().stream().map(s -> new User().setId(s)).collect(toList());

        Comment comment = Comment.builder()
                .content(params.getContent())
                .favorUsers(favorUsers)
                .createUser(new User().setId(params.getCreateUser()))
                .build();

        mongoTemplate.insert(comment);
    }

    @GetMapping("/{commentId}")
    public Comment get(@PathVariable("commentId") String id) {
        return commentRepository.findById(id).get();
    }
}
