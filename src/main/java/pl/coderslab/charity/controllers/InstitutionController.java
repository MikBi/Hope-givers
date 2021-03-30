package pl.coderslab.charity.controllers;


import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/secretCave/institutions")
@Secured("ROLE_ADMIN_MEN")
public class InstitutionController {

    private final InstitutionService institutionService;
    private static final String COMEBACK = "redirect:";

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("")
    public String institutions(Model model) {
        model.addAttribute("institutions", institutionService.all());
        model.addAttribute("newInstitution", new Institution());
        return "admin/instList";
    }

    @PostMapping("/add")
    public String institutionsAddPost(@Valid Institution institution, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/instList";
        }

        institutionService.add(institution);
        return COMEBACK;
    }

    @GetMapping("/edit")
    public String institutionsEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("institution", institutionService.findOne(id));
        return "admin/instEdit";
    }

    @PostMapping("/edit")
    public String institutionsEditPost(@Valid Institution institution, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/instEdit";
        }

        institutionService.add(institution);
        return COMEBACK;
    }

    @GetMapping("/delete")
    public String institutionsDelete(@RequestParam int id) {
        institutionService.delete(id);
        return COMEBACK;
    }


}
