package com.example.demo.web;

import com.example.demo.models.bindings.AddAnimalBinding;
import com.example.demo.models.bindings.AddWorkerBinding;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.AnimalView;
import com.example.demo.services.AnimalService;
import com.example.demo.services.ShelterService;
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
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Controller
public class AnimalController {
    private ModelMapper modelMapper;
    private ShelterService shelterService;
    private UserService userService;
    private AnimalService animalService;

    public AnimalController(ModelMapper modelMapper, ShelterService shelterService, UserService userService, AnimalService animalService) {
        this.modelMapper = modelMapper;
        this.shelterService = shelterService;
        this.userService = userService;
        this.animalService = animalService;
    }

    @ModelAttribute
    public AddAnimalBinding addAnimalBinding(){
        return new AddAnimalBinding();
    }

    @GetMapping("/user/shelter/add-animal")
    public String getAddAnimal(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        if (userService.getById(currentUser.getId()).getShelter().getName()==null){
            return "redirect:/user/add-shelter";
        }
        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        return "add-animal";
    }
    @PostMapping(value = "/user/shelter/add-animal")
    public String postAddAnimal(@Valid AddAnimalBinding addAnimalBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser)  {

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
        } catch ( IOException e) {
            e.printStackTrace();
        }


        return "redirect:/";
    }


    @GetMapping("/user/{userId}/shelter/animal/{id}/details")
    public String getAnimalDetails(Model model, @PathVariable Long userId, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser){

        if (userService.getById(userId)==null||userService.getById(userId).getShelter()==null){
            return "redirect:/error404";
        }
        AnimalView animalView = userService.getById(userId).getShelter().getAnimals().stream().filter(w -> Objects.equals(w.getId(), id)).map(w -> modelMapper.map(w,AnimalView.class)).findFirst().orElse(null);
        if (animalView==null){
            return "redirect:/error404";
        }

        model.addAttribute("mine",Objects.equals(userId, currentUser.getId()));

        model.addAttribute("animal",animalView);


        return "animal-details";
    }
    @DeleteMapping("/user/{uId}/animal/{id}/delete")
    public String deleteAnimal( @PathVariable Long id) {
        animalService.deleteById(id);

        return "redirect:/";
    }



    @GetMapping("/animal/{id}/update")
    public String getUpdateAnimal (Model model,@AuthenticationPrincipal CurrentUser currentUser,@PathVariable Long id){
        if (!animalService.contains(id)){
            return "redirect:/error404";
        }
        if (userService.getById(currentUser.getId()).getShelter().getName()==null){
            return "redirect:/user/add-shelter";
        }

        if (!animalService.checkIsItMine(id,currentUser.getId())){
            return "redirect:/";
        }




        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        model.addAttribute("id",id);


        return "update-animal";
    }
    @PatchMapping("/animal/{id}/update")
    public String updateAnimal(@Valid AddAnimalBinding addAnimalBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id)  {




        String fileName= StringUtils.cleanPath(addAnimalBinding.getImage().getOriginalFilename());
        if (bindingResult.hasErrors()||fileName.length()<1||fileName.contains("..")) {
            if (fileName.length()<1||fileName.contains("..")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addAnimalBinding", addAnimalBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAnimalBinding", bindingResult);
            return "redirect:/animal/"+id+"/update";
        }

        try {
            String image= Base64.getEncoder().encodeToString(addAnimalBinding.getImage().getBytes());

            animalService.updateAnimal(modelMapper.map(addAnimalBinding, AnimalView.class).setImage(image),id);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
