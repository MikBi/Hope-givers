package pl.coderslab.charity.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.services.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin_Island/categories")
@Secured("ROLE_ADMIN_PLACE")
public class CategoryController {

    private final CategoryService categoryService;
    private static final String COMEBACK = "redirect:";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String categories(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.all());
        return "admin/categoryList";
    }

    @PostMapping("/add")
    public String categoryAddPost(@Valid Category category, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/categoryList";
        }
        categoryService.add(category);
        return COMEBACK;
    }

    @GetMapping("/edit")
    public String categoryEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("category", categoryService.findOne(id));
        return "admin/catEdit";
    }

    @PostMapping("/edit")
    public String categoryEditPost(@Valid Category category, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/catEdit";
        }
        categoryService.add(category);
        return COMEBACK;
    }

    @GetMapping("/delete")
    public String categoryDelete(@RequestParam int id) {
        categoryService.delete(id);
        return COMEBACK;
    }



}
