package lol.kent.practice.core.framework.exception;

import lol.kent.practice.core.http.ErrorResponse;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月04日 19:34
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class ErrorResponseBuilder {


    public static ErrorResponse build(String code, String message, Throwable ex ) {

        return new ErrorResponse(code, message, ex == null ? null:ex.getMessage());
    }

}
