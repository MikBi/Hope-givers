package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.services.DonationService;


@Controller
@RequestMapping("/person")
@Secured("ROLE_USER_PERSON")
public class UserController {

    private final DonationService donationService;

    public UserController(DonationService donationService) {
        this.donationService = donationService;
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model,@AuthenticationPrincipal CurrentUser user) {
        model.addAttribute("dono", donationService.mySeven(user.getUser().getId()));
        model.addAttribute("Cuser", user.getUser().getName());
        return "user/dashboard";
    }

    @GetMapping("/donations")
    public String dono(@AuthenticationPrincipal CurrentUser user, Model model) {
    model.addAttribute("dono", donationService.allMine(user.getUser().getId()));
    return "user/donos";
    }

    @ModelAttribute("UserAmount")
    public Integer amount(@AuthenticationPrincipal CurrentUser user) {
        return donationService.myAmount(user.getUser().getId());
    }

}
