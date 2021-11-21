package com.example.demo.web;

import com.example.demo.models.bindings.SearchBinding;
import com.example.demo.models.view.AnimalView;
import com.example.demo.services.AnimalService;
import com.example.demo.services.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private TownService townService;
    private AnimalService animalService;

    public SearchController(TownService townService, AnimalService animalService) {
        this.townService = townService;
        this.animalService = animalService;
    }
    @ModelAttribute()
    public SearchBinding searchBinding(){
        return new SearchBinding();
    }

    @GetMapping("/search")
    public String getSearch(Model model) {

        model.addAttribute("towns",townService.getAll());

        return "search";
    }

    @PostMapping("/search")
    public String updateWorker(@Valid SearchBinding searchBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes)  {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchBinding", searchBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.searchBinding", bindingResult);
            return "redirect:/search";
        }

        List<AnimalView> animals=animalService.getAllSearched(searchBinding);
        redirectAttributes.addFlashAttribute("animals",animals);

        return "redirect:/search/result";
    }

    @GetMapping("/search/result")
    public String getResult(Model model) {
        if (!model.containsAttribute("animals")){
            model.addAttribute("animals",new ArrayList<AnimalView>());
           return "redirect:/search";
        }



        return "result";
    }
}
