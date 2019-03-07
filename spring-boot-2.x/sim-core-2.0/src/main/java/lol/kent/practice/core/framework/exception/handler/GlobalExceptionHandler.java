package lol.kent.practice.core.framework.exception.handler;

import lol.kent.practice.core.framework.exception.BusinessException;
import lol.kent.practice.core.framework.exception.ErrorResponseBuilder;
import lol.kent.practice.core.http.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月04日 19:00
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Value("${framework.trace.print:true}")
    private final boolean TRACE_PRINT = true;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ErrorResponse handleBusinessException(BusinessException exception, HttpServletRequest request, Throwable ex) {
        String code = exception.getCode() == null ? this.getStatus(request).toString() : exception.getCode();
        if (TRACE_PRINT) {
            return ErrorResponseBuilder.build(code, exception.getMessage(), ex);
        } else {
            return ErrorResponseBuilder.build(code, exception.getMessage(), null);

        }
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
