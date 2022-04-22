package com.example.demo.model.error;

public class PostGatewayException extends RuntimeException {
    private static final long serialVersionUID = 3008000300799708626L;

    public PostGatewayException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
