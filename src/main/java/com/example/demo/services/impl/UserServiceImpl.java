package com.example.demo.services.impl;

import com.example.demo.models.entities.Image;
import com.example.demo.models.entities.Shelter;
import com.example.demo.models.entities.User;
import com.example.demo.models.enums.UserType;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.services.RegisterService;
import com.example.demo.models.view.MessagesView;
import com.example.demo.models.view.UserView;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TownService;
import com.example.demo.services.UserRoleService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    private final TownService townService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ModelMapper modelMapper, TownService townService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.townService = townService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existByEmail(String value) {
       return  userRepository.existsByEmail(value);
    }

    @Override
    public boolean existByUsername(String value) {
       return userRepository.existsByUsername(value);

    }

    @Override
    public boolean match(String email, String password) {
        User byEmail = userRepository.findByEmail(email);
        return byEmail.getPassword().equals(password);
    }
    @Override
    public boolean isOwner(Long principalId,Long ownerId){
        return Objects.equals(principalId, ownerId);
    }

    @Override
    public boolean banned(String email) {

        return userRepository.findByEmail(email)==null? false : userRepository.findByEmail(email).getBanned();

    }



    @Override
    public User register(RegisterService service) {
        User user = modelMapper.map(service, User.class).setTown(townService.getByName(service.getTown())).setPassword(passwordEncoder.encode(service.getPassword()));
        if (service.getUserType() == UserType.SHELTER){
            user.setShelter(new Shelter().setUser(user));
            user.getRoles().add(userRoleService.getRoleById(3L));
        }else {
            user.getRoles().add(userRoleService.getRoleById(2L));
        }


        userRepository.save(user);
          return user;
    }

    @Override
    public List<UserView> getAllUsers() {
      return userRepository.findAll().stream().filter(s->!s.getRoles().contains(userRoleService.getRoleById(1L))).map(user -> modelMapper.map(user,UserView.class).setTown(user.getTown().getName()).setRegisteredAsShelter(user.getShelter()!=null).setIsBanned(user.getBanned())).collect(Collectors.toList());

    }

    @Override
    public void banUser(Long id) {
        userRepository.save(userRepository.getById(id).setBanned(true));

    }

    @Override
    public List<MessagesView> getMessagesFrom(Long id) {
       return   userRepository.getById(id).getMine().stream().map(m->modelMapper.map(m, MessagesView.class).setFrom(m.getFrom().getUsername()).setTo(m.getTo().getUsername())).collect(Collectors.toList());
    }
    @Override
    public List<MessagesView> getMessagesTo(Long id) {
        return   userRepository.getById(id).getTheirs().stream().map(m->modelMapper.map(m, MessagesView.class).setFrom(m.getFrom().getUsername()).setTo(m.getTo().getUsername())).collect(Collectors.toList());
    }

    @Override
    public User getById(Long id)  {
        User byId = userRepository.findById(id).orElse(null);

        return byId;
    }

    @Override
    public User getByName(String name) {
        return userRepository.getByUsername(name);
    }

    @Override
    public void unbanUser(Long id) {
        userRepository.save(userRepository.getById(id).setBanned(false));
    }

    @Override
    public void saveShelterByUserId(Long id, AddShelterService shelter) {
        User user = userRepository.findById(id).orElse(null);

        user.getShelter().setName(shelter.getName()).setDescription(shelter.getDescription()).getImage().setUrl(shelter.getImageUrl()).setPublicId(shelter.getImageId());
        userRepository.save(user);

    }

    @Override
    public void save(User byId) {
        userRepository.save(byId);
    }


}
