package lol.kent.practice.spring.mongo.dao;

import lol.kent.practice.spring.mongo.entity.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年08月25日 16:02
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public interface PostRepository extends CrudRepository<Post, String> {

    Post getPostById(String id);
}
