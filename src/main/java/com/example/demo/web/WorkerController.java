package com.example.demo.web;

import com.example.demo.models.bindings.AddShelterBinding;
import com.example.demo.models.bindings.AddWorkerBinding;
import com.example.demo.models.services.AddShelterService;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.WorkerView;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import com.example.demo.services.WorkerService;
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
public class WorkerController {

    private ModelMapper modelMapper;
    private ShelterService shelterService;
    private UserService userService;
    private WorkerService workerService;

    public WorkerController(ModelMapper modelMapper, ShelterService shelterService, UserService userService, WorkerService workerService) {
        this.modelMapper = modelMapper;
        this.shelterService = shelterService;
        this.userService = userService;
        this.workerService = workerService;
    }



    @ModelAttribute
    public AddWorkerBinding addWorkerBinding(){
        return new AddWorkerBinding();
    }

    @GetMapping("/user/shelter/add-worker")
    public String getAddWorker(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        if (userService.getById(currentUser.getId()).getShelter().getName()==null){
            return "redirect:/user/add-shelter";
        }
        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        return "add-worker";
    }
    @PostMapping(value = "/user/shelter/add-worker")
    public String postAddWorker(@Valid AddWorkerBinding addWorkerBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser)  {


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


    @GetMapping("/user/{userId}/shelter/worker/{id}/details")
    public String getWorkerDetails(Model model, @PathVariable Long userId,@PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser){

        if (userService.getById(userId)==null||userService.getById(userId).getShelter()==null){
            return "redirect:/error404";
        }
        WorkerView workerView = userService.getById(userId).getShelter().getWorkers().stream().filter(w -> Objects.equals(w.getId(), id)).map(w -> modelMapper.map(w, WorkerView.class).setFullName(w.getFirstName() + " " + w.getLastName())).findFirst().orElse(null);
        if (workerView==null){
            return "redirect:/error404";
        }

            model.addAttribute("mine",Objects.equals(userId, currentUser.getId()));

          model.addAttribute("worker",workerView);


        return "worker-details";
    }
    @DeleteMapping("/user/{uId}/worker/{id}/delete")
    public String deleteWorker( @PathVariable Long id) {
        workerService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("worker/{id}/update")
    public String getUpdateWorker (Model model,@AuthenticationPrincipal CurrentUser currentUser,@PathVariable Long id){
        if (!workerService.contains(id)){
            return "redirect:/error404";
        }
        if (userService.getById(currentUser.getId()).getShelter().getName()==null){
            return "redirect:/user/add-shelter";
        }

        if (!workerService.checkIsItMine(id,currentUser.getId())){
            return "redirect:/";
        }




        if (!model.containsAttribute("incorrectImage")){
            model.addAttribute("incorrectImage",false);
        }
        model.addAttribute("id",id);


        return "update-worker";
    }
    @PatchMapping("/worker/{id}/update")
    public String updateWorker(@Valid AddWorkerBinding addWorkerBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes,@PathVariable Long id)  {




        String fileName= StringUtils.cleanPath(addWorkerBinding.getImage().getOriginalFilename());
        if (bindingResult.hasErrors()||fileName.length()<1||fileName.contains("..")) {
            if (fileName.length()<1||fileName.contains("..")){
                redirectAttributes.addFlashAttribute("incorrectImage",true);
            }
            redirectAttributes.addFlashAttribute("addWorkerBinding", addWorkerBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWorkerBinding", bindingResult);
            return "redirect:/worker/{id}/update";
        }

        try {
            String image= Base64.getEncoder().encodeToString(addWorkerBinding.getImage().getBytes());

            workerService.updateWorker(modelMapper.map(addWorkerBinding, AddWorkerService.class).setImage(image),id);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
