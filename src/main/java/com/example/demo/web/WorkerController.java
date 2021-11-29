package com.example.demo.web;

import com.example.demo.models.bindings.AddWorkerBinding;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.WorkerView;
import com.example.demo.services.CloudinaryService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import com.example.demo.services.WorkerService;
import com.example.demo.services.impl.CloudinaryImage;
import com.example.demo.services.impl.CurrentUser;
import com.example.demo.utils.exeptions.ObjectNotFoundExeption;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
public class WorkerController {

    private ModelMapper modelMapper;
    private ShelterService shelterService;
    private UserService userService;
    private WorkerService workerService;
    private CloudinaryService cloudinaryService;

    public WorkerController(ModelMapper modelMapper, ShelterService shelterService, UserService userService, WorkerService workerService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.shelterService = shelterService;
        this.userService = userService;
        this.workerService = workerService;
        this.cloudinaryService = cloudinaryService;
    }


    @ModelAttribute
    public AddWorkerBinding addWorkerBinding() {
        return new AddWorkerBinding();
    }

    @GetMapping("/shelter/add-worker")
    public String getAddWorker(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (userService.getById(currentUser.getId()).getShelter().getName() == null) {
            return "redirect:/user/add-shelter";
        }
        if (!model.containsAttribute("incorrectImage")) {
            model.addAttribute("incorrectImage", false);
        }
        return "add-worker";
    }

    @PostMapping(value = "/shelter/add-worker")
    public String postAddWorker(@Valid AddWorkerBinding addWorkerBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {


        if (bindingResult.hasErrors() || Objects.equals(addWorkerBinding.getImage().getOriginalFilename(), "")) {
            if (Objects.equals(addWorkerBinding.getImage().getOriginalFilename(), "")) {
                redirectAttributes.addFlashAttribute("incorrectImage", true);
            }
            redirectAttributes.addFlashAttribute("addWorkerBinding", addWorkerBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWorkerBinding", bindingResult);
            return "redirect:/shelter/add-worker";
        }

        CloudinaryImage upload = this.cloudinaryService.upload(addWorkerBinding.getImage());
        shelterService.addWorker(modelMapper.map(addWorkerBinding, AddWorkerService.class), upload.getUrl(), upload.getPublicId(), currentUser.getId());


        return "redirect:/";
    }


    @GetMapping("/user/{userId}/shelter/worker/{id}/details")
    public String getWorkerDetails(Model model, @PathVariable Long userId, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) throws NotFoundException {

        if (userService.getById(userId) == null || userService.getById(userId).getShelter() == null) {
           throw new ObjectNotFoundExeption("Not found");
        }
        WorkerView workerView = workerService.getWorkerView(id);

        if (workerView == null) {
            throw new ObjectNotFoundExeption("Not found");
        }

        model.addAttribute("mine", Objects.equals(userId, currentUser.getId()));

        model.addAttribute("worker", workerView);


        return "worker-details";
    }

    @DeleteMapping("/worker/{id}/delete")
    public String deleteWorker(@PathVariable Long id,@AuthenticationPrincipal CurrentUser currentUser) {
        workerService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("worker/{id}/update")
    public String getUpdateWorker(Model model, @AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long id) throws NotFoundException {
        if (!workerService.contains(id)) {
            throw new ObjectNotFoundExeption("Not found");
        }
        if (userService.getById(currentUser.getId()).getShelter().getName() == null) {
            return "redirect:/user/add-shelter";
        }

        if (!workerService.checkIsItMine(id, currentUser.getId())) {
            return "redirect:/";
        }
        WorkerView workerView = workerService.getWorkerView(id);
        String[] name = workerView.getFullName().split(" ");

        model.addAttribute("addWorkerBinding", new AddWorkerBinding().setFirstName(name[0]).setLastName(name[1]).setDescription(workerView.getDescription()));
        if (!model.containsAttribute("incorrectImage")) {
            model.addAttribute("incorrectImage", false);
        }
        if (!model.containsAttribute("incorrectFirstName")) {
            model.addAttribute("incorrectFirstName", false);
        }
        if (!model.containsAttribute("incorrectLastName")) {
            model.addAttribute("incorrectLastName", false);
        }
        model.addAttribute("id", id);


        return "update-worker";
    }

    @PatchMapping("/worker/{id}/update")
    public String updateWorker(@Valid AddWorkerBinding addWorkerBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id) throws IOException {

        cloudinaryService.delete(workerService.getById(id).getImage().getPublicId());


        if (bindingResult.hasErrors()|| Objects.equals(addWorkerBinding.getImage().getOriginalFilename(), "")) {
            if (Objects.equals(addWorkerBinding.getImage().getOriginalFilename(), "")) {
                redirectAttributes.addFlashAttribute("incorrectImage", true);
            }
            if (addWorkerBinding.getFirstName().length()<1||addWorkerBinding.getFirstName().length()>10){
                redirectAttributes.addFlashAttribute("incorrectFirstName",true);
            }
            if (addWorkerBinding.getLastName().length()<1||addWorkerBinding.getLastName().length()>10){
                redirectAttributes.addFlashAttribute("incorrectLastName",true);
            }
            return "redirect:/worker/{id}/update";
        }

        CloudinaryImage upload = cloudinaryService.upload(addWorkerBinding.getImage());

        workerService.updateWorker(modelMapper.map(addWorkerBinding, AddWorkerService.class), upload.getUrl(), upload.getPublicId(), id);


        return "redirect:/";
    }

}
