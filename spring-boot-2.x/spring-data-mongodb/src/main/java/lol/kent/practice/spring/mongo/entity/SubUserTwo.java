package lol.kent.practice.spring.mongo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 *    类描述: 测试子类在不声明@Document情况下是否能插入DB
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年01月13日 15:08
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
public class SubUserTwo extends User {

    private String addr;
}
