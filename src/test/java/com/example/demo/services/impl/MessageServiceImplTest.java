package com.example.demo.services.impl;

import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    private MessageService messageService;

    @BeforeEach
    void init() {



    }

}