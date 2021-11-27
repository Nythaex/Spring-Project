package com.example.demo.web.exeptions;

public class ObjectNotFoundExeption extends RuntimeException{
    public ObjectNotFoundExeption(String message) {
        super(message);
    }
}
