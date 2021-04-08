package pl.coderslab.charity.controllers;

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
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private static final String REGISTER = "register";

    public HomeController(DonationService donationService, InstitutionService institutionService, UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @RequestMapping("/")
    public String homeAction() {
        return "index";
    }

    @ModelAttribute("donationsSum")
    public Integer donations() {
        return donationService.donationsSum();
    }

    @ModelAttribute("donationsQuantitySum")
    public Integer quantity() {
        return donationService.donationsQuantitySum();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.all();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return REGISTER;
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, @RequestParam String repass) {

        if (result.hasErrors()) {
            return REGISTER;
        }

        if (!user.getPassword().equals(repass)) {
            return REGISTER;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.single(1));
        userService.add(user);
        return "login";
    }
}
