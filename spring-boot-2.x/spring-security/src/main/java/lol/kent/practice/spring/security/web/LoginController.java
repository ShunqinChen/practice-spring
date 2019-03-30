package lol.kent.practice.spring.security.web;

import lol.kent.practice.spring.security.user.domain.CustomUser;
import lol.kent.practice.spring.security.web.response.BearTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月26日 19:29
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version x.x.x
 */
@RestController
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping("/auth/login")
    public BearTokenResponse login(@RequestBody CustomUser user, HttpServletResponse response) {
        logger.info("custom");
        String header = response.getHeader("HEADER_STRING");
        return new BearTokenResponse(header);

    }


}
