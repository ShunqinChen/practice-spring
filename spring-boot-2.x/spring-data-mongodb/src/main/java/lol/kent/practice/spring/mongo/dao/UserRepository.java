package lol.kent.practice.spring.mongo.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import java.util.regex.Pattern;
import lol.kent.practice.spring.mongo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.Repository;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月01日 11:23
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqi n.Chen
 * @version 1.0.0
 */
@Slf4j
@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User, String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public User save(String name, int age, String mail) {
        User user = new User();
        user.setAge(age);
//        user.setName(name.concat("_").concat(String.valueOf(System.currentTimeMillis())));
        user.setName(name);
        user.setMail(mail);
        return mongoTemplate.insert(user);
    }

    public User save(User user) {
        return mongoTemplate.insert(user);
    }

    public BasicDBObject get(String name) throws JsonProcessingException {
        Pattern pattern = Pattern.compile(name, 'i');
        Criteria filter = Criteria.where("name").regex(pattern);
        MatchOperation match = match(filter);
        LookupOperation lookup = Aggregation.lookup("user", "name", "name", "realName");
        UnwindOperation unwind = Aggregation.unwind("realName", true);

        // Method 1
        Aggregation aggregation = Aggregation.newAggregation(unwind, match, lookup);
        AggregationResults<BasicDBObject> result = mongoTemplate
                .aggregate(aggregation, "user", BasicDBObject.class);
        log.info("Normal agg:{}",
                objectMapper.writeValueAsString(result.getMappedResults().get(0)));

        // Method 2
        TypedAggregation<User> pipeline = TypedAggregation
                .newAggregation(User.class, match, lookup, unwind);
        AggregationResults<User> users = mongoTemplate.aggregate(pipeline, User.class);
        log.info("Type agg:{}", objectMapper.writeValueAsString(users));

        return result.getUniqueMappedResult();

    }

    public User returnOnlyTwoFields(String name) {

        Criteria filter = Criteria.where("name").is(name);
        Query query = Query.query(filter);
        query.fields().include("name").include("id");
        return mongoTemplate.findOne(query, User.class);
    }

}
