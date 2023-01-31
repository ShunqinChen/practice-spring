package lol.kent.practice.spring.mongo.dao;

import lol.kent.practice.spring.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年01月19日 16:43
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public interface UserMongoRepository extends MongoRepository<User, String> {

}
