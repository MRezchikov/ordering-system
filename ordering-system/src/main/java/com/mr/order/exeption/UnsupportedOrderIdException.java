package com.mr.order.exeption;

public class UnsupportedOrderIdException extends RuntimeException {

    public UnsupportedOrderIdException(String message) {
        super(message);
    }
}
