package com.example.demo.web;

import com.example.demo.models.bindings.AddWorkerBinding;
import com.example.demo.models.bindings.SearchBinding;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.AnimalView;
import com.example.demo.services.AnimalService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.TownService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

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


    @GetMapping("/error404")
    public String get404() {
        return "not-found";
    }


    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal CurrentUser user) {
        if (userService.getById(user.getId()).getShelter() != null) {
            if (userService.getById(user.getId()).getShelter().getImage() != null) {

                return "redirect:/user/" + user.getId() + "/shelter";

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
