package lol.kent.practice.spring.mongo.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年03月09日 14:39
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@Document("test_profile")
public class UserProfile {

    @Id
    private String id;

    private List<ObjectId> userIds;

    public void addUserId(ObjectId userId) {
        if (userIds == null) {
            userIds = new ArrayList<>();
        }

        userIds.add(userId);
    }
}
