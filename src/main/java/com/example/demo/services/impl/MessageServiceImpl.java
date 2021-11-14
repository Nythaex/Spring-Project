package com.example.demo.services.impl;

import com.example.demo.models.entities.Message;
import com.example.demo.models.services.SentMessageService;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, UserService userService) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void sentMessage(String name, SentMessageService messageService) {
        Message message=modelMapper.map(messageService,Message.class).setFrom(userService.getByName(name)).setTo(userService.getByName(messageService.getUsername()));
        messageRepository.save(message);
    }
}
