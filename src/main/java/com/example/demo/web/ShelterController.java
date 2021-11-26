package com.example.demo.web;

import com.example.demo.models.bindings.AddShelterBinding;
import com.example.demo.models.entities.User;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.view.ShelterView;
import com.example.demo.models.view.WorkerView;
import com.example.demo.services.AnimalService;
import com.example.demo.services.CloudinaryService;
import com.example.demo.services.UserService;
import com.example.demo.services.WorkerService;
import com.example.demo.services.impl.CloudinaryImage;
import com.example.demo.services.impl.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShelterController {
    private UserService userService;
    private AnimalService animalService;
    private WorkerService workerService;
    private ModelMapper modelMapper;
    private CloudinaryService cloudinaryService;

    public ShelterController(UserService userService, AnimalService animalService, WorkerService workerService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.animalService = animalService;
        this.workerService = workerService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/user/{id}/shelter")
    public String getShelterHome(Model model , @PathVariable Long id,@AuthenticationPrincipal CurrentUser user) {
        User userById = userService.getById(id);
        if (userById ==null|| userById.getShelter()==null|| userById.getShelter().getName()==null|| userById.getBanned()){
            return "redirect:/error404";
        }

                ShelterView shelterView = modelMapper.map(userById.getShelter(), ShelterView.class).setUsername(userById.getUsername()).setImage(userById.getShelter().getImage().getUrl());

        List<WorkerView> collect = userById.getShelter().getWorkers().stream().map(s -> modelMapper.map(s, WorkerView.class).setFullName(s.getFirstName() + " " + s.getLastName())).collect(Collectors.toList());
        shelterView.setWorkers(collect);

                model.addAttribute("userId",id);
                model.addAttribute("shelter",shelterView);
                model.addAttribute("mine",user.getId().toString().equals(id.toString()));
                model.addAttribute("animals",shelterView.getAnimals().stream().sorted((s1,s2)->s1.getId().compareTo(s2.getId())).map(a->animalService.getAnimalView(a.getId())).collect(Collectors.toList()));
                model.addAttribute("workers",shelterView.getWorkers().stream().sorted((s1,s2)->s1.getId().compareTo(s2.getId())).map(w->workerService.getWorkerView(w.getId())).collect(Collectors.toList()));



        return "shelter-home";
    }



    @ModelAttribute
    public AddShelterBinding addShelterBinding(){
        return new AddShelterBinding().setImage(null);
    }

    @GetMapping("/user/add-shelter")
    public String getAddShelter (Model model,@AuthenticationPrincipal CurrentUser currentUser){
        if (userService.getById(currentUser.getId()).getShelter().getName()!=null){
            return "redirect:/user/"+currentUser.getId()+"/shelter";
        }

        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        return "add-shelter";
    }
    @PostMapping(value = "/user/add-shelter")
    public String postAddShelter(@Valid AddShelterBinding addShelterBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {


        if (bindingResult.hasErrors()||addShelterBinding.getImage().getOriginalFilename().equals("")) {
            if (addShelterBinding.getImage().getOriginalFilename().equals("")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addShelterBinding", addShelterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShelterBinding", bindingResult);
            return "redirect:/user/add-shelter";
        }

        CloudinaryImage upload = this.cloudinaryService.upload(addShelterBinding.getImage());
            userService.saveShelterByUserId(currentUser.getId(),modelMapper.map(addShelterBinding, AddShelterService.class).setImageUrl(upload.getUrl()).setImageId(upload.getPublicId()));


        return "redirect:/";
    }


    @GetMapping("/user/shelter/update")
    public String getUpdateShelter (Model model,@AuthenticationPrincipal CurrentUser currentUser){
        User userById = userService.getById(currentUser.getId());
        if (userById.getShelter().getName()==null){
            return "redirect:/user/add-shelter";
        }
        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }

       model.addAttribute("addShelterBinding",new AddShelterBinding().setName(userById.getShelter().getName()).setDescription(userById.getShelter().getDescription()));
        return "update-shelter";
    }
        @PatchMapping("/user/shelter/update")
    public String updateShelter(@Valid AddShelterBinding addShelterBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {

         cloudinaryService.delete(userService.getById(currentUser.getId()).getShelter().getImage().getPublicId());


        if (bindingResult.hasErrors()||addShelterBinding.getImage().getOriginalFilename().equals("")) {
            if (addShelterBinding.getImage().getOriginalFilename().equals("")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addShelterBinding", addShelterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShelterBinding", bindingResult);
            return "redirect:/user/shelter/update";
        }

            CloudinaryImage upload = this.cloudinaryService.upload(addShelterBinding.getImage());
            userService.saveShelterByUserId(currentUser.getId(),modelMapper.map(addShelterBinding, AddShelterService.class).setImageUrl(upload.getUrl()).setImageId(upload.getPublicId()));


        return "redirect:/";
    }




}
