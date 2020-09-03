package lol.kent.practice.spring.mongo.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月03日 10:19
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
public class CommentDTO {

    private String content;

    private List<String> favorUsers;

    private String createUser;

    @Tolerate
    public CommentDTO() {
    }
}
