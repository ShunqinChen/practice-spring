package lol.kent.core.monitor;

import lol.kent.practice.core.framework.spring.test.SpringBaseTest;
import org.junit.Test;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月07日 15:00
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class MonitorControllerTest extends SpringBaseTest {

    @Test
    public void health() throws Exception {
        get("/health");
    }

}
