package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.CurrentUser;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.CategoryService;
import pl.coderslab.charity.services.InstitutionService;
import pl.coderslab.charity.services.RoleService;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/secretCave")
@Secured("ROLE_ADMIN_MEN")
public class AdminController {

    private final InstitutionService institutionService;
    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminController(InstitutionService institutionService, UserService userService, RoleService roleService, CategoryService categoryService) {
        this.institutionService = institutionService;
        this.userService = userService;
        this.roleService = roleService;
        this.categoryService = categoryService;
    }

    @GetMapping("/dashboard")
    private String dashboard() {
        return "dashboard";
    }

    @GetMapping("/institutions")
    private String institutions(Model model) {
        model.addAttribute("institutions", institutionService.all());
        model.addAttribute("newInstitution", new Institution());
        return "instList";
    }

    @PostMapping("instAdd")
    private String institutionsAddPost(@Valid Institution institution, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }

        institutionService.add(institution);
        return "redirect:institutions";
    }

    @GetMapping("instEdit")
    private String institutionsEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("institution", institutionService.single(id));
        return "instEdit";
    }

    @PostMapping("instEdit")
    private String institutionsEditPost(@Valid Institution institution, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }

        institutionService.add(institution);
        return "redirect:institutions";
    }

    @GetMapping("instDelete")
    private String institutionsDelete(@RequestParam int id) {
        institutionService.delete(id);
        return "redirect:institutions";
    }

    @GetMapping("/users")
    private String users(Model model, @AuthenticationPrincipal CurrentUser user) {
        User u = userService.single(user.getUser().getId());
        model.addAttribute("actualUser", u);
        model.addAttribute("users", userService.allButWithoutOurselves(user.getUser().getId()));
        return "userList";
    }

    @GetMapping("/userAdd")
    private String userAdd(Model model) {
        model.addAttribute("user", new User());
        return "userAdd";
    }

    @PostMapping("userAdd")
    private String userAddPost(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }

        int res = getRandomNumber(1, Integer.MAX_VALUE);

        if (user.getRole().getId().equals(2)) {
            while (userService.IdCheck(res) != null) {
                res = getRandomNumber(1, Integer.MAX_VALUE);
            }
            user.setAdminId(res);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:users";
    }

    @GetMapping("userEdit")
    private String userEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("user", userService.single(id));
        return "editUser";
    }

    @PostMapping("userEdit")
    private String userEditPost(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }
        int res = getRandomNumber(1, Integer.MAX_VALUE);
        if (user.getRole().getId().equals(2)) {
            while (userService.IdCheck(res) != null) {
                res = getRandomNumber(1, Integer.MAX_VALUE);
            }
            user.setAdminId(res);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:users";
    }

    @GetMapping("userDelete")
    private String userDelete(@RequestParam int id) {
        userService.delete(id);
        return "redirect:users";
    }

    @GetMapping("categories")
    private String categories(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.all());
        return "categoryList";
    }

    @PostMapping("catAdd")
    private String categoryAddPost(@Valid Category category, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }
        categoryService.add(category);
        return "redirect:categories";
    }

    @GetMapping("catEdit")
    private String categoryEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("category", categoryService.single(id));
        return "catEdit";
    }

    @PostMapping("catEdit")
    private String categoryEditPost(@Valid Category category, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }
        categoryService.add(category);
        return "redirect:categories";
    }

    @GetMapping("catDelete")
    private String categoryDelete(@RequestParam int id) {
        categoryService.delete(id);
        return "redirect:categories";
    }

    @GetMapping("/test")
    private String test() {
        return "blank";
    }

    @ModelAttribute("roles")
    private List<Role> roles() {
        return roleService.all();
    }

@ModelAttribute("Cuser")
private CurrentUser currentUser(@AuthenticationPrincipal CurrentUser user) {
        return user;
}

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
