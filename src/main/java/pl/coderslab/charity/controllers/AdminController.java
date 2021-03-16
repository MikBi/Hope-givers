package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secretCave")
@Secured("ROLE_ADMIN_MEN")
public class AdminController {
}
