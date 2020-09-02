package lol.kent.practice.spring.mongo.framework.mongo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *    类描述: MongoDB Manual Reference
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月02日 10:51
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Populate {

    Class<?> ref();

    String as() default "_as";
}
