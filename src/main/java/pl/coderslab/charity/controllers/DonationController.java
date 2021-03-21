package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/donation")
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
        return "form";
    }

    @PostMapping("/give")
    public String postGive(@Valid Donation donation, @RequestParam List<Integer> categories, @RequestParam Integer bagsNum, @RequestParam Integer institutions, @RequestParam String data, @RequestParam String time, @RequestParam String more_info) {

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
        donationService.add(donation);
        return "form-confirmation";
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
