package lol.kent.practice.spring.mongo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月01日 19:09
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@Builder
@Accessors(chain = true)
public class UserDTO {

    private String name;

    private Integer age;

    private String mail;

    @Tolerate
    public UserDTO() {
    }
}
