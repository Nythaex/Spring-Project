package com.example.demo.web;

import com.example.demo.models.bindings.AddShelterBinding;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.view.ShelterView;
import com.example.demo.models.view.WorkerView;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CurrentUser;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShelterController {
    private UserService userService;
    private ModelMapper modelMapper;

    public ShelterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/user/{id}/shelter")
    public String getShelterHome(Model model , @PathVariable Long id,@AuthenticationPrincipal CurrentUser user) {
        if (userService.getById(id)==null||userService.getById(id).getShelter()==null||userService.getById(id).getShelter().getName()==null|| userService.getById(id).getBanned()){
            return "redirect:/error404";
        }

                ShelterView shelterView = modelMapper.map(userService.getById(id).getShelter(), ShelterView.class).setUsername(userService.getById(id).getUsername());

        List<WorkerView> collect = userService.getById(id).getShelter().getWorkers().stream().map(s -> modelMapper.map(s, WorkerView.class).setFullName(s.getFirstName() + " " + s.getLastName())).collect(Collectors.toList());
        shelterView.setWorkers(collect);

                model.addAttribute("userId",id);
                model.addAttribute("shelter",shelterView);
                model.addAttribute("mine",user.getId().toString().equals(id.toString()));
                model.addAttribute("animals",shelterView.getAnimals().stream().sorted((s1,s2)->s1.getId().compareTo(s2.getId())).collect(Collectors.toList()));
                model.addAttribute("workers",shelterView.getWorkers().stream().sorted((s1,s2)->s1.getId().compareTo(s2.getId())).collect(Collectors.toList()));



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
    public String postAddShelter(@Valid AddShelterBinding addShelterBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser)  {

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


    @GetMapping("/user/shelter/update")
    public String getUpdateShelter (Model model,@AuthenticationPrincipal CurrentUser currentUser){
        if (userService.getById(currentUser.getId()).getShelter().getName()==null){
            return "redirect:/user/add-shelter";
        }
        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }


        return "update-shelter";
    }
        @PatchMapping("/user/shelter/update")
    public String updateShelter(@Valid AddShelterBinding addShelterBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser)  {




        String fileName= StringUtils.cleanPath(addShelterBinding.getImage().getOriginalFilename());
        if (bindingResult.hasErrors()||fileName.length()<1||fileName.contains("..")) {
            if (fileName.length()<1||fileName.contains("..")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addShelterBinding", addShelterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addShelterBinding", bindingResult);
            return "redirect:/user/shelter/update";
        }

        try {
            String image= Base64.getEncoder().encodeToString(addShelterBinding.getImage().getBytes());
            userService.saveShelterByUserId(currentUser.getId(),modelMapper.map(addShelterBinding, AddShelterService.class).setImage(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }




}
