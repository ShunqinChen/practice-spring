package lol.kent.practice.spring.mongo;

import lol.kent.practice.spring.mongo.entity.UserProfile;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class SpringDataMongodbApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("Test size of Id")
    void contextLoads() {
        UserProfile profile = new UserProfile();
        for (int i = 0; i < 1000; i++) {
            profile.addUserId(new ObjectId());
        }
        mongoTemplate.save(profile);
    }

}
