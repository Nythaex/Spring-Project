package com.example.demo.web;


import com.example.demo.models.view.UserView;
import com.example.demo.services.MessageService;
import com.example.demo.services.StatService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller

public class AdminController {
    private UserService userService;
    private MessageService messageService;
    private StatService statService;

    public AdminController(UserService userService, MessageService messageService, StatService statService) {
        this.userService = userService;
        this.messageService = messageService;
        this.statService = statService;
    }


    @GetMapping("/admin")
    public String getAdmin(Model model){
        List<UserView> users=userService.getAllUsers();
        model.addAttribute("users",users);
        model.addAttribute("stats",statService.getStats());

        return "admin";
    }

    @PatchMapping("/admin/{id}/ban")
    public String banUser(@PathVariable Long id){
        userService.banUser(id);

        return "redirect:/admin";
    }
    @PatchMapping("/admin/{id}/unban")
    public String unbanUser(@PathVariable Long id){
        userService.unbanUser(id);

        return "redirect:/admin";
    }






}
