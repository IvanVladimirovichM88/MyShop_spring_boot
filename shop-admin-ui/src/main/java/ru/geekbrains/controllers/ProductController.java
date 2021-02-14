package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.CategoryService;
import ru.geekbrains.services.ProductService;

import javax.validation.Path;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String showAllProducts(
            Model model
    ){
        model.addAttribute("productsData", productService.getAllProductsData());
        return "products";
    }

    @GetMapping("/add")
    public String ShowFormAddProduct(
            Model model
    ){
        model.addAttribute("productData", new ProductData());
        model.addAttribute("allCategoriesData", categoryService.getAllCategoriesData());
        return "forms/form_add_product";
    }

    @PostMapping("/add")
    public String addProduct(
            @ModelAttribute ProductData productData
    ){
        productService.createOrUpdateProduct(productData);
        return "redirect:/products";
    }

    @GetMapping("/edit/{productId}")
    public String ShowFormEditProduct(
            @PathVariable Long productId,
            Model model
    ){
        model.addAttribute("productData", productService.getProductDataById(productId));
        model.addAttribute("allCategories", categoryService.getAllCategoriesData());
        return "forms/form_edit_product";
    }

    @PostMapping("/edit")
    public String EditProduct(
            @ModelAttribute ProductData productData
    ){
        productService.createOrUpdateProduct(productData);
        return "redirect:/products";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(
            @PathVariable Long productId
    ){
        productService.remove(productId);
        return "redirect:/products";
    }
}
