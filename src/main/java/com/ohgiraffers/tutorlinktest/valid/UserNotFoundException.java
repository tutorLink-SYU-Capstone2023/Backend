package com.ohgiraffers.tutorlinktest.valid;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}