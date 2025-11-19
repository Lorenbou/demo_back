package br.com.satc.demo.web.errors;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
