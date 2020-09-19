package lol.kent.practice.spring.mongo.dao;

import lol.kent.practice.spring.mongo.entity.Comment;

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
public interface CommentExtRepository {

    Comment findOneById(String id);
}
