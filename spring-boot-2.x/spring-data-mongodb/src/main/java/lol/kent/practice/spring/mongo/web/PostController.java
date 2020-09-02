package lol.kent.practice.spring.mongo.web;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObjectBuilder;
import java.util.Arrays;
import java.util.Map;
import lol.kent.practice.spring.mongo.dao.PostRepository;
import lol.kent.practice.spring.mongo.dto.PostDTO;
import lol.kent.practice.spring.mongo.entity.Post;
import lol.kent.practice.spring.mongo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月01日 18:52
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PutMapping("/{postId}")
    public void edit(@RequestBody PostDTO request) {
        return;
    }

    @PostMapping
    public void create(@RequestBody PostDTO request) {

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setUser(new ObjectId(request.getUserId()));
        post.setCommentUserIds(Arrays.asList(
                new ObjectId("5f4e2d3232024228b1ada556"),
                new ObjectId("5f4f56d7b692e62231f19134")
        ));

        User createUser = new User();
        createUser.setId(request.getUserId());

        post.setCreateBy(createUser);
        post.setUpdateBy(new ObjectId(request.getUserId()));

        postRepository.save(post);

    }

    @PostMapping("/mapping")
    public void postByMap(@RequestBody Map<String, Object> params) throws JsonProcessingException {
        log.info("post by map:{} collection:{}", objectMapper.writeValueAsString(params),
                Post.class.getSimpleName());
        BSONObject obj = new BasicBSONObject(params);

        mongoTemplate.insert(obj, "post");
    }


    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{postId}")
    public Post get(@PathVariable("postId") String postId) throws JsonProcessingException {

        Query query = new BasicQuery("{id: '5f4e3744fe76bb716bf03bb6'}");
        Post record = mongoTemplate.findOne(query, Post.class);
        log.info("findONe:{}", objectMapper.writeValueAsString(record));

        record = null;
        BSONObject params = BasicDBObjectBuilder.start().add("_id", postId).get();
        Query queryByBsonObj = new BasicQuery(params.toString());
        record = mongoTemplate.findOne(queryByBsonObj, Post.class);
        log.info("findOne by BsonObject:{}", objectMapper.writeValueAsString(record));

        String filter = BasicDBObjectBuilder.start("id", new ObjectId(postId)).get().toString();
        record = mongoTemplate.findOne(new BasicQuery(filter), Post.class);
        log.info("findOne By Str:{}", record);

//        filter = new BasicBSONObject("id", postId).toString(); // wrong print val is: `id=xxx` not a json
//        log.info("filter:{}", filter);

        Document filterDoc = new Document("id", postId);
        record = mongoTemplate.findOne(new BasicQuery(filterDoc), Post.class);
        log.info("findOne 4:{}", objectMapper.writeValueAsString(record));

        MatchOperation match = match(Criteria.where("_id").is(postId));
        TypedAggregation<Post> aggregation = TypedAggregation
                .newAggregation(Post.class,
                        match,
                        lookup("user", "updateBy", "_id", "updateUser"),
                        unwind("updateUser"));
        AggregationResults<Post> results = mongoTemplate.aggregate(aggregation, Post.class);

        return results.getUniqueMappedResult();
    }

    @GetMapping("/populate/{postId}")
    public Post customerAnnotationTest(@PathVariable("postId") String postId) {
        String filter = BasicDBObjectBuilder.start("id", new ObjectId(postId)).get().toString();
        Post record = mongoTemplate.findOne(new BasicQuery(filter), Post.class);
        log.info("findOne By Str:{}", record);
        return record;
    }
}
