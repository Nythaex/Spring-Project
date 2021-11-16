package com.example.demo.web;

import com.example.demo.models.bindings.SendMessageBinding;
import com.example.demo.models.services.SentMessageService;
import com.example.demo.models.view.MessagesView;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MessageController {
    private UserService userService;
    private MessageService messageService;
    private ModelMapper modelMapper;

    public MessageController(UserService userService, MessageService messageService, ModelMapper modelMapper) {
        this.userService = userService;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/user/messages")
    public String redirectToMyMessages(@AuthenticationPrincipal CurrentUser currentUser) {

        return "redirect:/user/" + currentUser.getId() + "/messages";
    }


    @GetMapping("/user/{id}/messages")
    public String getAllMessages(Model model, @PathVariable Long id, HttpServletRequest request, Principal principal) {



        List<MessagesView> messages;
        if (request.isUserInRole("ROLE_ADMIN")) {
            messages = userService.getMessagesFrom(id);
        } else if (principal.getName().equals(userService.getById(id).getUsername())) {
            messages = userService.getMessagesTo(id);
        } else {
            throw new AuthorizationServiceException("Dont have an access");
        }
        model.addAttribute("messages", messages);
        model.addAttribute("userId", id);

        return "messages";
    }

    @DeleteMapping("/user/{uId}/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long uId, @PathVariable Long id) {
        messageService.deleteById(id);

        return "redirect:/user/" + uId + "/messages";
    }

    @ModelAttribute
    public SendMessageBinding sendMessageBinding() {
        return new SendMessageBinding();
    }

    @GetMapping("/user/messages/sent")
    public String getSentMessage(Model model) {
           if (!model.containsAttribute("contains")){
               model.addAttribute("contains",true);
           }

        return "sent-message";
    }

    @PostMapping("/user/messages/sent")
    public String postSentMessage(@Valid SendMessageBinding sendMessageBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {



        if (bindingResult.hasErrors()||userService.getByName(sendMessageBinding.getUsername())==null) {

            redirectAttributes.addFlashAttribute("sendMessageBinding", sendMessageBinding);


                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.sendMessageBinding", bindingResult);

            if (userService.getByName(sendMessageBinding.getUsername())==null){
                redirectAttributes.addFlashAttribute("contains",false);
            }
            return "redirect:/user/messages/sent";
        }



        messageService.sentMessage(httpServletRequest.getUserPrincipal().getName(), modelMapper.map(sendMessageBinding, SentMessageService.class));


        return "redirect:/";
    }
}
