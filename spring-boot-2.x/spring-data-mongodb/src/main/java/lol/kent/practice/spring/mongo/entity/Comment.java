package lol.kent.practice.spring.mongo.entity;

import java.util.Date;
import java.util.List;
import lol.kent.practice.spring.mongo.framework.mongo.MongoIdRef;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月03日 10:09
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@Builder
@Accessors(chain = true)
@Document("comment")
public class Comment {

    @Id
    private String id;

    private String content;

    @MongoIdRef
    private List<User> favorUsers;

    @MongoIdRef
    private User createUser;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @Tolerate
    public Comment() {
    }
}

