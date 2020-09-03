package lol.kent.practice.spring.mongo.dao;

import lol.kent.practice.spring.mongo.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月03日 12:19
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

}
