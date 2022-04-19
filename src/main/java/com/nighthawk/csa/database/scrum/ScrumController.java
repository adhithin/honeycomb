package com.nighthawk.csa.database.scrum;

import com.nighthawk.csa.database.person.PersonSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

// Built using video: https://www.youtube.com/watch?v=ctwRpskAeIU
@Controller
public class ScrumController implements WebMvcConfigurer {

    @Autowired
    private ScrumSqlRepository scrumSqlRepository;

    @Autowired
    private PersonSqlRepository personSqlRepository;

    @GetMapping("/database/scrum")
    public String scrumTeam(Model model) {
        model.addAttribute("list", scrumSqlRepository.listAll());
        return "database/scrum";
    }

    /*  The HTML template Forms and Model attributes are bound
        @return - Template for form
        @param -  Class for form
    */
    @GetMapping("/database/scrum_create")
    public String scrumTeamCreate(Model model) {
        model.addAttribute("scrum", new Scrum());
        model.addAttribute("listPersons", personSqlRepository.listAll());
        return "database/scrum_form";
    }

    @GetMapping("/database/scrum_update/{id}")
    public String scrumTeamUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);  //passed to support using one form
        model.addAttribute("scrum", scrumSqlRepository.get(id));
        model.addAttribute("listPersons", personSqlRepository.listAll());
        return "database/scrum_form";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/database/scrum_save")
    public String saveData(@Valid Scrum scrum, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Validation of Family attributes, validation of nested object supported
        if (bindingResult.hasErrors()) {
            return "database/scrum_form";
        }
        // Redirect to next step
        scrumSqlRepository.save(scrum);
        return "redirect:/database/scrum";
    }

    @GetMapping("/database/scrum_delete/{id}")
    public String familyDelete(@PathVariable("id") long id) {
        scrumSqlRepository.delete(id);
        return "redirect:/database/scrum";
    }
}