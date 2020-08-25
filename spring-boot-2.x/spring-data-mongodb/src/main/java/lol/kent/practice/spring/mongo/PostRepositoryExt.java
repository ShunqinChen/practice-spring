package lol.kent.practice.spring.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年08月25日 16:15
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@Repository
public class PostRepositoryExt implements PostRepository {

    @Override
    public Post getPostById(String id) {
        log.info("Override...........");
        return null;
    }
}
