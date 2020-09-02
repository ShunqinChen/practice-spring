package lol.kent.practice.spring.mongo.entity;

import java.util.List;
import lol.kent.practice.spring.mongo.framework.mongo.Populate;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年08月25日 14:15
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@Document("post")
public class Post {

    @Id
    private String id;

    private String title;

    private ObjectId user;

    @DBRef
    private User createBy;

    @Populate(ref = User.class, as = "realUser")
    private ObjectId updateBy;

    private User updateUser;

    private User realUser;

    @Populate(ref = User.class, as = "commentUsers")
    private List<ObjectId> commentUserIds;

    private List<User> commentUsers;

}
