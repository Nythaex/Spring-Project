package com.example.demo.utils.exeptions;

public class ObjectNotFoundExeption extends RuntimeException{
    public ObjectNotFoundExeption(String message) {
        super(message);
    }
}
