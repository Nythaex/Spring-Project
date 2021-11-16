package com.example.demo.web;

import com.example.demo.models.bindings.AddAnimalBinding;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.impl.CurrentUser;
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
public class AnimalController {
    private ModelMapper modelMapper;
    private ShelterService shelterService;

    public AnimalController(ModelMapper modelMapper, ShelterService shelterService) {
        this.modelMapper = modelMapper;
        this.shelterService = shelterService;
    }

    @ModelAttribute
    public AddAnimalBinding addAnimalBinding(){
        return new AddAnimalBinding();
    }

    @GetMapping("/user/shelter/add-animal")
    public String getAddAnimal(Model model){
        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        return "add-animal";
    }
    @PostMapping(value = "/user/shelter/add-animal")
    public String postAddShelter(@Valid AddAnimalBinding addAnimalBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser)  {

        String fileName= StringUtils.cleanPath(addAnimalBinding.getImage().getOriginalFilename());
        if (bindingResult.hasErrors()||fileName.length()<1||fileName.contains("..")) {
            if (fileName.length()<1||fileName.contains("..")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addAnimalBinding", addAnimalBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAnimalBinding", bindingResult);
            return "redirect:/user/shelter/add-animal";
        }

        try {
            String image= Base64.getEncoder().encodeToString(addAnimalBinding.getImage().getBytes());
            shelterService.addAnimal(modelMapper.map(addAnimalBinding, AddAnimalService.class).setImage(image),currentUser.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/";
    }
}
