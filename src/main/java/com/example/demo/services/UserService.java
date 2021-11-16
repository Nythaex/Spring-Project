package com.example.demo.services;

import com.example.demo.models.entities.User;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.services.RegisterService;
import com.example.demo.models.view.MessagesView;
import com.example.demo.models.view.UserView;

import java.util.List;

public interface UserService {
    boolean existByEmail(String value);

    boolean existByUsername(String value);

    boolean match(String email, String password);

    boolean banned(String email);

    void register(RegisterService service);

    List<UserView> getAllUsers();

    void banUser(Long id);


    List<MessagesView> getMessagesFrom(Long id);

    List<MessagesView> getMessagesTo(Long id);

    User getById(Long id);

    User getByName(String name);

    void unbanUser(Long id);

    void saveShelterByUserId(Long id, AddShelterService setImage);


    void save(User byId);
}
