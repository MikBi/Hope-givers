package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.entities.*;
import pl.coderslab.charity.services.*;
import java.util.List;

@Controller
@RequestMapping("/secretCave")
@Secured("ROLE_ADMIN_MEN")
public class AdminController {

    private final InstitutionService institutionService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final DonationService donationService;

    public AdminController(InstitutionService institutionService, UserService userService, CategoryService categoryService, DonationService donationService) {
        this.institutionService = institutionService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.donationService = donationService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }


    @ModelAttribute("Cuser")
    private CurrentUser currentUser(@AuthenticationPrincipal CurrentUser user) {
        return user;
    }

    @ModelAttribute("in24h")
    private Integer hours() {
        return donationService.last24h();
    }

    @ModelAttribute("QuantitySum")
    private Integer quantity() {
        return donationService.donationsQuantitySum();
    }

    @ModelAttribute("useres")
    private Integer amountOfUsers() {
        return userService.amount();
    }

    @ModelAttribute("instSum")
    private Integer sum() {
        return institutionService.sum();
    }

    @ModelAttribute("donations")
    private List<Donation> sev() {
        return donationService.seven();
    }

    @ModelAttribute("categories")
    private List<Category> categoryList() {
        return categoryService.all();
    }
}
