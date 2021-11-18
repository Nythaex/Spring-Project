package com.example.demo.web;

import com.example.demo.models.bindings.AddShelterBinding;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.view.AnimalView;
import com.example.demo.models.view.ShelterView;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CurrentUser;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private ShelterService shelterService;
    private UserService userService;
    private ModelMapper modelMapper;


    public HomeController(ShelterService shelterService, UserService userService, ModelMapper modelMapper) {
        this.shelterService = shelterService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/error404")
    public String get404(){
        return "not-found";
    }

    @GetMapping("/")
    public String getHome(Model model ,@AuthenticationPrincipal CurrentUser user) {
     if (userService.getById(user.getId()).getShelter()!=null){
         if (userService.getById(user.getId()).getShelter().getImage()!=null){

             return "redirect:/user/"+user.getId()+"/shelter";

         }else {
              return "redirect:/user/add-shelter";
         }

        }


        return "home";
    }



    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }
}
