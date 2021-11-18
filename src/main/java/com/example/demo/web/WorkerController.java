package com.example.demo.web;

import com.example.demo.models.bindings.AddAnimalBinding;
import com.example.demo.models.bindings.AddWorkerBinding;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.services.AddWorkerService;
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
public class WorkerController {

    private ModelMapper modelMapper;
    private ShelterService shelterService;

    public WorkerController(ModelMapper modelMapper, ShelterService shelterService) {
        this.modelMapper = modelMapper;
        this.shelterService = shelterService;
    }



    @ModelAttribute
    public AddWorkerBinding addWorkerBinding(){
        return new AddWorkerBinding();
    }

    @GetMapping("/user/shelter/add-worker")
    public String getAddWorker(Model model){
        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        return "add-worker";
    }
    @PostMapping(value = "/user/shelter/add-worker")
    public String postAddShelter(@Valid AddWorkerBinding addWorkerBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser)  {

        String fileName= StringUtils.cleanPath(addWorkerBinding.getImage().getOriginalFilename());
        if (bindingResult.hasErrors()||fileName.length()<1||fileName.contains("..")) {
            if (fileName.length()<1||fileName.contains("..")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addWorkerBinding", addWorkerBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWorkerBinding", bindingResult);
            return "redirect:/user/shelter/add-worker";
        }

        try {
            String image= Base64.getEncoder().encodeToString(addWorkerBinding.getImage().getBytes());
            shelterService.addWorker(modelMapper.map(addWorkerBinding, AddWorkerService.class).setImage(image),currentUser.getId());
        } catch ( IOException e) {
            e.printStackTrace();
        }


        return "redirect:/";
    }
}
