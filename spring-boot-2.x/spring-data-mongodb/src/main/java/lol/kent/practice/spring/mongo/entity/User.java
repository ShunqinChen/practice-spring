package lol.kent.practice.spring.mongo.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年08月25日 14:13
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@Document("user")
public class User {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String mail;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date updatedTime;

    @CreatedBy
    private String createdUserId;

    @LastModifiedBy
    private String upatedUserId;

    @Tolerate
    public User() {
    }

    public User(String id, String name, Integer age, String mail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
    }
}
