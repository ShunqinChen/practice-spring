package lol.kent.practice.spring.mongo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lol.kent.practice.spring.mongo.dao.PostRepository;
import lol.kent.practice.spring.mongo.entity.Post;
import lol.kent.practice.spring.mongo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Slf4j
@EnableMongoAuditing
@SpringBootApplication
public class Application {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper1;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    //    @PostConstruct
    public void getByRepo() throws JsonProcessingException {
        ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();
        Post post = postRepository.getPostById("5f44b21a3d554379c156398d");
        log.info("GET POST:{}",
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post));
    }

    public void get() throws JsonProcessingException {

        LookupOperation lookupOperation = LookupOperation.newLookup().from("user")
                .localField("user")
                .foreignField("_id")
                .as("authors");
        Criteria filter = Criteria.where("_id").is("5f44b21a3d554379c156398d");
        Aggregation aggregation = newAggregation(match(filter), lookupOperation,
                project("createBy"));

        List<Post> posts = mongoTemplate.aggregate(aggregation, "post", Post.class)
                .getMappedResults();
        log.info("Get post:{}",
                objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(posts));

        // DBRef test
//        Post post = mongoTemplate.findOne(Query.query(filter), Post.class);
//        log.info("GET POST:{}", post);
    }

    public User saveUser() {
        User user = new User();
        user.setName("Kent_".concat(String.valueOf(System.currentTimeMillis())));
        return mongoTemplate.insert(user);
    }

    public void savePost() {
        log.info("提交POST");
        User user = this.saveUser();
        log.info("USER ID: {}", user.getId());
        Post post = new Post();
        post.setTitle("测试".concat(String.valueOf(System.currentTimeMillis())));
        post.setUser(new ObjectId(user.getId()));
        post.setCreateBy(user);
        mongoTemplate.insert(post);
        log.info("POST ID: {}", post.getId());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
