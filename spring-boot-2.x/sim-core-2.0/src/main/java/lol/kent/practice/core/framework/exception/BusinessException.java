package lol.kent.practice.core.framework.exception;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月04日 19:04
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class BusinessException extends RuntimeException {

    private String code;

    private String message;

    public BusinessException() {
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
