package lol.kent.practice.core.http;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年03月04日 19:27
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class ErrorResponse<T> {

    private String code;

    private String message;

    private String stacktrace;

    private T data;

    private static final ErrorResponse<?> EMPTY = new ErrorResponse<>();

    public ErrorResponse() {
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(String code, String message, String stacktrace) {
        this.code = code;
        this.message = message;
        this.stacktrace = stacktrace;
    }

    public ErrorResponse(String code, String message, String stacktrace, T data) {
        this.code = code;
        this.message = message;
        this.stacktrace = stacktrace;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
