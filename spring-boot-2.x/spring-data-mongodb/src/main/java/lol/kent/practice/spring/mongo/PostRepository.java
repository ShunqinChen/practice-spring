package lol.kent.practice.spring.mongo;

import org.springframework.data.repository.Repository;

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
public interface PostRepository extends Repository<Post, String> {

    Post getPostById(String id);
}
