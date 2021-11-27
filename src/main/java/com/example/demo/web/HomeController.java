package com.example.demo.web;

import com.example.demo.services.ShelterService;
import com.example.demo.services.TownService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private ShelterService shelterService;
    private UserService userService;

    private TownService townService;


    public HomeController(ShelterService shelterService, UserService userService, TownService townService) {
        this.shelterService = shelterService;
        this.userService = userService;

        this.townService = townService;
    }




    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal CurrentUser user) {
        if (userService.getById(user.getId()).getShelter() != null) {
            if (userService.getById(user.getId()).getShelter()==null) {

                return "redirect:/shelter/" + user.getId() ;

            } else {
                return "redirect:/user/add-shelter";
            }
        }

        model.addAttribute("towns",townService.getAll());

        return "redirect:/search";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}
