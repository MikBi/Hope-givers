package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
@Secured("ROLE_USER_PERSON")
public class UserController {

    @GetMapping("/dashboard")
    @ResponseBody
    private String dashboard() {
        return "dashboard";
    }
}
