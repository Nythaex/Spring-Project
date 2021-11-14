package com.example.demo.services;

import com.example.demo.models.services.SentMessageService;

public interface MessageService {

    void deleteById(Long id);

    void sentMessage(String name, SentMessageService map);
}
