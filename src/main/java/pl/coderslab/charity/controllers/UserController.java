package pl.coderslab.charity.controllers;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.RoleService;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin_Island/users")
@Secured("ROLE_ADMIN_PLACE")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private static final String COMEBACK = "redirect:";

    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String users(Model model, @AuthenticationPrincipal CurrentUser user) {
        User u = userService.findOne(user.getUser().getId());
        model.addAttribute("actualUser", u);
        model.addAttribute("users", userService.allButWithoutOurselves(user.getUser().getId()));
        return "admin/userList";
    }

    @GetMapping("/add")
    public String userAdd(Model model) {
        model.addAttribute("user", new User());
        return "admin/userAdd";
    }

    @PostMapping("/add")
    public String userAddPost(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/userAdd";
        }
        int res = getRandomNumber(1, Integer.MAX_VALUE);

        if (user.getRole().getId().equals(2)) {
            while (userService.idCheck(res) != null) {
                res = getRandomNumber(1, Integer.MAX_VALUE);
            }
            user.setAdminId(res);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return COMEBACK;
    }

    @GetMapping("/edit")
    public String userEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "admin/userEdit";
    }

    @PostMapping("/edit")
    public String userEditPost(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/userEdit";
        }

        if(user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
            userService.add(user);
            return COMEBACK;
    }

    @GetMapping("/delete")
    public String userDelete(@RequestParam int id, @AuthenticationPrincipal CurrentUser user) {

        if (userService.adminList().size() == 1) {
            return COMEBACK;
        }

        if (id == user.getUser().getId()) {
            userService.delete(id);
            return "/";
        }

        userService.delete(id);
        return COMEBACK;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @ModelAttribute("admins")
    private List<User> admins() {
        return userService.adminList();
    }

    @ModelAttribute("roles")
    private List<Role> roles() {
        return roleService.all();
    }

}
