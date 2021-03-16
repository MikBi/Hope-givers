package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public HomeController(DonationService donationService, InstitutionService institutionService, CategoryService categoryService, UserService userService, RoleService roleService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping("/")
    private String homeAction() {
        return "index";
    }


    @ModelAttribute("donationsSum")
    private Integer donations() {
        return donationService.donationsSum();
    }

    @ModelAttribute("donationsQuantitySum")
    private Integer quantity() {
        return donationService.donationsQuantitySum();
    }

    @ModelAttribute("institutions")
    private List<Institution> institutions() {
        return institutionService.all();
    }

    @GetMapping("/login")
    private String login() {
        return "login";
    }

    @GetMapping("/logout")
    private String logout() {
        return "logout";
    }

    @GetMapping("/register")
    private String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    private String register(@Valid User user, BindingResult result, @RequestParam String pass, @RequestParam String repass) {

        if (result.hasErrors()) {
            return "register";
        }

        if (!pass.equals(repass)) {
            return "register";
        }
        user.setPassword(passwordEncoder.encode(pass));
        user.setRole(roleService.single(1));
        userService.add(user);
        return "login";
    }
}
