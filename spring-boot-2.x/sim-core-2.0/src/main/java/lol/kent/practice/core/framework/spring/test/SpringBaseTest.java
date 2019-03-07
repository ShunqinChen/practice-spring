package lol.kent.practice.core.framework.spring.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月07日 18:43
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AbstractTestConfig.class)
@WebMvcTest
public class SpringBaseTest {

    @Autowired
    protected MockMvc mockMvc;

    protected ResultActions get(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(MockMvcResultHandlers.print());
    }


}
