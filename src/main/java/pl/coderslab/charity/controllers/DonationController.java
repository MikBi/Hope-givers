package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.*;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/donation")
@Secured({"ROLE_ADMIN_PLACE", "ROLE_USER_DONOR"})
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/give")
    public String give(Model model) {
        model.addAttribute("donation", new Donation());
        return "user/dono";
    }

    @PostMapping("/give")
    public String postGive(@Valid Donation donation, @AuthenticationPrincipal CurrentUser currentUser) {
        donation.setDate(LocalDateTime.now());
        User user = currentUser.getUser();
        donation.setUser(user);
        donation.setStatus(Status.REGISTERED);
        donation.setArchived(false);
        donationService.add(donation);
        return "user/DonoConfirmation";
    }

    @ModelAttribute("categories")
    private List<Category> names() {
        return categoryService.all();
    }

    @ModelAttribute("institutions")
    private List<Institution> institutions() {
        return institutionService.all();
    }

}
