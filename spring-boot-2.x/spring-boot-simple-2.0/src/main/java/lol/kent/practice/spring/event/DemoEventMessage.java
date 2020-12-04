package lol.kent.practice.spring.event;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月04日 17:11
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
@Builder
public class DemoEventMessage {

    private String type;

    private String message;

    @Tolerate
    public DemoEventMessage() {
    }

    public DemoEventMessage(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
