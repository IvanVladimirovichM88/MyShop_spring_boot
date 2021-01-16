package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.data.CategoryData;
import ru.geekbrains.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String ShowAllCategories(
            Model model
    ){
        model.addAttribute("allCategories", categoryService.getAllCategoriesData());
        return "categories";
    }

    @GetMapping("/add")
    public String showFormAddCategories(
            Model model
    ){
        model.addAttribute("newCategory", new CategoryData());
        return "forms/form_add_category";
    }

    @PostMapping("/add")
    public String addCategories(
            @ModelAttribute CategoryData categoryData
    ){
        categoryService.createOrUpdateCategory(categoryData);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{categoryId}")
    public String showFormEditCategory(
            @PathVariable Long categoryId,
            Model model
    ){
        model.addAttribute("categoryData", categoryService.getByIdCategoryData(categoryId));
        return "forms/form_edit_category";
    }

    @PostMapping("/edit")
    public String editCategory(
            Model model,
            @ModelAttribute CategoryData categoryData
    ){
        categoryService.createOrUpdateCategory(categoryData);
        return "redirect:/categories";
    }

}
