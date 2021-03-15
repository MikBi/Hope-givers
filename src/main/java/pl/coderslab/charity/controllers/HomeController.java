package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;
import java.util.List;


@Controller
public class HomeController {

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    public HomeController(DonationService donationService, InstitutionService institutionService, CategoryService categoryService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
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


}
