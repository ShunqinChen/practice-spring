package lol.kent.practice.spring.mongo.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月01日 19:04
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@Builder
public class PostDTO {

    private String title;

    private String userId;

    private List<String> commentUserIds;

    @Tolerate
    public PostDTO() {
    }
}
