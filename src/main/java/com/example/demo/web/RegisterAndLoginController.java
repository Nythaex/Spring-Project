package com.example.demo.web;


import com.example.demo.models.bindings.LoginBinding;
import com.example.demo.models.bindings.RegisterBinding;
import com.example.demo.models.enums.UserType;
import com.example.demo.models.services.RegisterService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.TownService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class RegisterAndLoginController {
    private UserService userService;
    private ShelterService shelterService;
    private ModelMapper modelMapper;
    private TownService townService;

    public RegisterAndLoginController(UserService userService, ShelterService shelterService, ModelMapper modelMapper, TownService townService) {
        this.userService = userService;
        this.shelterService = shelterService;
        this.modelMapper = modelMapper;
        this.townService = townService;
    }

    @ModelAttribute
    public RegisterBinding registerBinding() {
        return new RegisterBinding();
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("passMatch")) {
            model.addAttribute("passMatch", true);
        }
        model.addAttribute("towns", townService.getAll());


        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid RegisterBinding registerBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerBinding", registerBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerBinding", bindingResult);
            return "redirect:/register";
        } else if (!registerBinding.getPassword().equals(registerBinding.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerBinding", registerBinding);
            redirectAttributes.addFlashAttribute("passMatch", false);
            return "redirect:/register";
        }

        userService.register(modelMapper.map(registerBinding, RegisterService.class));


        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(@ModelAttribute("email")
                                String email, Model model) {

        if (!model.containsAttribute("banned")) {
            model.addAttribute("banned", false);
        }
        if (!model.containsAttribute("failed")) {
            model.addAttribute("failed", false);
        }


        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute("email")
                    String email,
            RedirectAttributes redirectAttributes) {
        if (userService.banned(email)) {
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("banned", true);
        }else {
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("failed", true);
        }

        return "redirect:/login";

    }

//    @PostMapping("/login")
//    public String postLogin(@Valid LoginBinding loginBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("loginBinding", loginBinding);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginBinding", bindingResult);
//            return "redirect:/login";
//        } else if (!userService.existByEmail(loginBinding.getEmail())) {
//            redirectAttributes.addFlashAttribute("loginBinding", loginBinding);
//            redirectAttributes.addFlashAttribute("notExists", false);
//            return "redirect:/login";
//        } else if (!userService.match(loginBinding.getEmail(), loginBinding.getPassword())) {
//            redirectAttributes.addFlashAttribute("loginBinding", loginBinding);
//            redirectAttributes.addFlashAttribute("match", false);
//            return "redirect:/login";
//        }
//        if (userService.banned(loginBinding.getEmail())) {
//            redirectAttributes.addFlashAttribute("loginBinding", loginBinding);
//            redirectAttributes.addFlashAttribute("notBanned", false);
//            return "redirect:/login";
//        }
//        userService.login(loginBinding);
//
//
//        return "home";
//    }


}
