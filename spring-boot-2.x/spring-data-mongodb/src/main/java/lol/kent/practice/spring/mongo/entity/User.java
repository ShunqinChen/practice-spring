package lol.kent.practice.spring.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.Id;
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

    @Tolerate
    public User() {
    }
}
