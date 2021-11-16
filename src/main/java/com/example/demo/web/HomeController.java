package com.example.demo.web;

import com.example.demo.models.bindings.AddShelterBinding;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.view.ShelterView;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CurrentUser;
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



    @GetMapping("/")
    public String getHome(Model model ,@AuthenticationPrincipal CurrentUser user){
     if (userService.getById(user.getId()).getShelter()!=null){
         if (userService.getById(user.getId()).getShelter().getImage()!=null){
             ShelterView shelterView = modelMapper.map(userService.getById(user.getId()).getShelter(), ShelterView.class).setUsername(user.getUsername());
             model.addAttribute("shelter",shelterView);
             model.addAttribute("animals",shelterView.getAnimals());

         }else {
              return "redirect:/user/add-shelter";
         }

        }


        return "home";
    }

    @ModelAttribute
    public AddShelterBinding addShelterBinding(){
        return new AddShelterBinding().setImage(null);
    }

    @GetMapping("/user/add-shelter")
    public String getAddShelter (Model model){
         if (!model.containsAttribute("incorrectImage")){
             model.addAttribute("incorrectImage",false);
         }

        return "add-shelter";
    }
    @PostMapping(value = "/user/add-shelter")
    public String postAddShelter(@Valid AddShelterBinding addShelterBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes,@AuthenticationPrincipal CurrentUser currentUser)  {

        String fileName= StringUtils.cleanPath(addShelterBinding.getImage().getOriginalFilename());
        if (bindingResult.hasErrors()||fileName.length()<1||fileName.contains("..")) {
            if (fileName.length()<1||fileName.contains("..")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addShelterBinding", addShelterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShelterBinding", bindingResult);
            return "redirect:/user/add-shelter";
        }

        try {
            String image= Base64.getEncoder().encodeToString(addShelterBinding.getImage().getBytes());
            userService.saveShelterByUserId(currentUser.getId(),modelMapper.map(addShelterBinding, AddShelterService.class).setImage(image));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/";
    }

    @GetMapping("/about")
    public String getAbout(){

        return "about";
    }
}
