package lol.kent.practice.spring.kotlin.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2022年01月24日 15:06
 * <p>
 * Company: Luoke101.com
 * <p>
 * @author Shunqin.Chen
 * @version 1.0.0
 *
 */
@RequestMapping("kotlin")
@RestController
open class KotlinTestController {

    @GetMapping("print")
    open fun print(@RequestParam("name") name: String): String {
        val action = "hello";
        return "$action $name";
    }
}
