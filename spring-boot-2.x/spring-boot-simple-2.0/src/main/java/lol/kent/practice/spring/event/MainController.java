package lol.kent.practice.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述: 事件发布机制Controller
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月04日 17:15
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 * @see <a href="https://blog.csdn.net/weixin_33832340/article/details/90650282?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-2.pc_relevant_default&utm_relevant_index=4">CSDN文章</a>
 */
@RestController
@RequestMapping("event")
public class MainController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("normal")
    public void testNormalListener() {
        DemoEventMessage msg = DemoEventMessage.builder()
                .type("demo")
                .message("他来了")
                .build();
        DemoEvent event = new DemoEvent(this, msg);
        event.setType("course");
        publisher.publishEvent(event);
    }

    @PostMapping("smart")
    public void testSmartListener() {
        DemoEventMessage msg = DemoEventMessage.builder()
                .type("demo")
                .message("他来了")
                .build();
        DemoEvent event = new DemoEvent(this, msg);
        event.setType("course");
        publisher.publishEvent(event);
    }
}
