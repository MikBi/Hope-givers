package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.StatusService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/donation")
@Secured({"ROLE_ADMIN_MEN", "ROLE_USER_PERSON"})
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final StatusService statusService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService, StatusService statusService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.statusService = statusService;
    }

    @GetMapping("/give")
    public String give(Model model) {
        model.addAttribute("donation", new Donation());
        return "user/form";
    }

    @PostMapping("/give")
    public String postGive(@Valid Donation donation, @RequestParam List<Integer> categories, @RequestParam Integer bagsNum, @RequestParam Integer institutions, @RequestParam String data, @RequestParam String time, @RequestParam String more_info, @AuthenticationPrincipal CurrentUser currentUser) {

        List<Category> cat = new ArrayList<>();
        categories.forEach(e -> cat.add(categoryService.single(e)));
        donation.setCategory(cat);
        donation.setQuantity(bagsNum);
        donation.setInstitution(institutionService.single(institutions));
        LocalDate date = LocalDate.parse(data);
        donation.setPickUpDate(date);
        LocalTime localTime = LocalTime.parse(time);
        donation.setPickUpTime(localTime);
        donation.setPickUpComment(more_info);
        LocalDateTime localDate =  LocalDateTime.now();
        donation.setDate(localDate);
        User user = currentUser.getUser();
        donation.setUser(user);
        donation.setStatus(statusService.single(2));
        donationService.add(donation);
        return "user/form-confirmation";
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
