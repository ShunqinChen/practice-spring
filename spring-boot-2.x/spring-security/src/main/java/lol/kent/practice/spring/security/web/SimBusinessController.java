package lol.kent.practice.spring.security.web;

import lol.kent.practice.spring.security.user.domain.BizDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标题、简要说明. <br>
 * 模拟正常业务类
 * <p>
 * Copyright: Copyright (c) 2019年03月27日 17:24
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version x.x.x
 */
@RestController("/biz")
public class SimBusinessController {

    @GetMapping
    public BizDTO doSomething() {
        BizDTO bizDTO = new BizDTO();
        bizDTO.setMusicPlayer("kentChensq");
        return bizDTO;
    }


}
