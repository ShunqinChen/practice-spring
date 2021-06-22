package lol.kent.practice.spring.socket.bean;

import lombok.Data;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年06月19日 18:21
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
public class JsonMessage {

    public String from;

    public String text;

    public String time;
}
