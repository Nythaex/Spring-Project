package com.example.demo.web;

import com.example.demo.models.bindings.AddAnimalBinding;
import com.example.demo.models.enums.AnimalTypes;
import com.example.demo.models.enums.Role;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.AnimalView;
import com.example.demo.services.AnimalService;
import com.example.demo.services.CloudinaryService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CloudinaryImage;
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
    private CloudinaryService cloudinaryService;

    public AnimalController(ModelMapper modelMapper, ShelterService shelterService, UserService userService, AnimalService animalService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.shelterService = shelterService;
        this.userService = userService;
        this.animalService = animalService;
        this.cloudinaryService = cloudinaryService;
    }

    @ModelAttribute
    public AddAnimalBinding addAnimalBinding() {
        return new AddAnimalBinding();
    }

    @GetMapping("/user/shelter/add-animal")
    public String getAddAnimal(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (userService.getById(currentUser.getId()).getShelter().getName() == null) {
            return "redirect:/user/add-shelter";
        }
        if (!model.containsAttribute("incorrectImage")) {
            model.addAttribute("incorrectImage", false);
        }
        return "add-animal";
    }

    @PostMapping(value = "/user/shelter/add-animal")
    public String postAddAnimal(@Valid AddAnimalBinding addAnimalBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {


        if (bindingResult.hasErrors() || addAnimalBinding.getImage().getOriginalFilename().equals("")) {
            if (addAnimalBinding.getImage().getOriginalFilename().equals("")) {
                redirectAttributes.addFlashAttribute("incorrectImage", true);
            }
            redirectAttributes.addFlashAttribute("addAnimalBinding", addAnimalBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAnimalBinding", bindingResult);
            return "redirect:/user/shelter/add-animal";

        }
        CloudinaryImage upload = cloudinaryService.upload(addAnimalBinding.getImage());

        shelterService.addAnimal(modelMapper.map(addAnimalBinding, AddAnimalService.class), upload.getUrl(), upload.getPublicId(), currentUser.getId());


        return "redirect:/";
    }


    @GetMapping("/user/{userId}/shelter/animal/{id}/details")
    public String getAnimalDetails(Model model, @PathVariable Long userId, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {


        if (userService.getById(userId) == null || userService.getById(userId).getShelter() == null) {
            return "redirect:/error404";
        }
        AnimalView animalView = animalService.getAnimalView(id);

        if (animalView == null) {
            return "redirect:/error404";
        }
        if (!Objects.equals(userId, currentUser.getId()) && userService.getById(currentUser.getId()).getRoles().stream().filter(r -> r.getRole().equals(Role.SHELTER)).findFirst().orElse(null) != null) {
            return "redirect:/";
        }


        model.addAttribute("mine", Objects.equals(userId, currentUser.getId()));

        model.addAttribute("animal", animalView);


        return "animal-details";
    }

    @DeleteMapping("/user/{uId}/animal/{id}/delete")
    public String deleteAnimal(@PathVariable Long id) {
        animalService.deleteById(id);

        return "redirect:/";
    }


    @GetMapping("/animal/{id}/update")
    public String getUpdateAnimal(Model model, @AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id) {
        if (!animalService.contains(id)) {
            return "redirect:/error404";
        }
        if (userService.getById(currentUser.getId()).getShelter().getName() == null) {
            return "redirect:/user/add-shelter";
        }

        if (!animalService.checkIsItMine(id, currentUser.getId())) {
            return "redirect:/";
        }


        AnimalView animalView = animalService.getAnimalView(id);

        model.addAttribute("addAnimalBinding", new AddAnimalBinding().setName(animalView.getName()).setDescription(animalView.getDescription())
                .setAnimalType(AnimalTypes.valueOf(animalView.getAnimalType())));
        if (!model.containsAttribute("incorrectImage")) {
            model.addAttribute("incorrectImage", false);
        }
        model.addAttribute("id", id);


        return "update-animal";
    }

    @PatchMapping("/animal/{id}/update")
    public String updateAnimal(@Valid AddAnimalBinding addAnimalBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id) throws IOException {

        cloudinaryService.delete(animalService.getById(id).getImage().getPublicId());

        if (bindingResult.hasErrors() || addAnimalBinding.getImage().getOriginalFilename().equals("")) {
            if (addAnimalBinding.getImage().getOriginalFilename().equals("")) {
                redirectAttributes.addFlashAttribute("incorrectImage", true);
            }
            redirectAttributes.addFlashAttribute("addAnimalBinding", addAnimalBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAnimalBinding", bindingResult);
            return "redirect:/animal/" + id + "/update";
        }


        CloudinaryImage upload = cloudinaryService.upload(addAnimalBinding.getImage());
        animalService.updateAnimal(modelMapper.map(addAnimalBinding, AddAnimalService.class).setImageUrl(upload.getUrl()).setImageId(upload.getPublicId()), id);


        return "redirect:/";
    }

}
