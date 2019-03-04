package lol.kent.practice.wechat.auth.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月01日 20:40
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
public class AuthContoller {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${wechat.passive.session.token}")
    private String token;

    /**
     * 接收微信消息的入口
     * @return  如果微信给与的签名与我方计算签名的结果一致则返回请求参数中的echostr字符串
     * */
    @GetMapping(value = "/passive-gateway")
    public String authFromWeChatToServer(@RequestParam("nonce")String nonce,@RequestParam("signature")String sign,@RequestParam("timestamp")String timestamp,@RequestParam("echostr")String echostr) {
        logger.info("WeChat verify url with param:{},{},{},{}",nonce,sign,timestamp,echostr);

        return echostr;
    }


    @PostMapping(value = "/passive-gateway")
    public String authFromWeChatToServer(@RequestBody String content) {
        logger.info("WeChat send msg:{} ", content);

        return "success";
    }
}
