package lol.kent.practice.spring.mongo.dao.impl;

import lol.kent.practice.spring.mongo.dao.CommentExtRepository;
import lol.kent.practice.spring.mongo.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月19日 18:47
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class CommentExtRepositoryImpl implements CommentExtRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Comment findOneById(String id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Comment.class);
    }
}
