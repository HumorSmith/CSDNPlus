package com.ifreedomer.cplus.http.protocol;

public class PayLoad<T> {
    public static final int SUCCESS = 2000;
    public static final int APP_SUCCESS = 0;
    private T data;
    private int code;
    private String message;
    private String sessionId;
    private String sessionExpired;

    public String getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(String sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PayLoad{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
