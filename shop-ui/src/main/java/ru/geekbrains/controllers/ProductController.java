package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.data.ShortLineItem;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.CategoryService;
import ru.geekbrains.services.ProductServiceForShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductServiceForShop productService;
    private CartService cartService;
    private CategoryService categoryService;

    public ProductController(ProductServiceForShop productService,
                             CartService cartService,
                             CategoryService categoryService
    ) {
        this.productService = productService;
        this.cartService = cartService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showAllProducts(
            Model model
    ){
        model.addAttribute("productsData", productService.findAllProducts());
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "shop";
    }

    @GetMapping("/details/{productId}")
    public String showDetails(
            Model model,
            @PathVariable(name = "productId") Long productId
    ){

        model.addAttribute("productData", productService.findProductDataById(productId));
        model.addAttribute("lineItem", new ShortLineItem(productId,0));

        return "shop-detail";
    }



    @PostMapping(value = "/details")
    public String addToCartFromDetail(
            @ModelAttribute ShortLineItem lineItem
    ){
        ProductData productData = productService.findProductDataById( lineItem.getProductId() );

        cartService.addSeveralAndUpdate(
                productService.findProductDataById( lineItem.getProductId() ),
                lineItem.getQty());
        return "redirect:/products";
    }


    @GetMapping("/addToCart/{productId}")
    public void addProductInCart(
            @PathVariable (name = "productId") Long productId,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        ProductData productData = productService.findProductDataById(productId);
        cartService.addOneAndUpdate(productData);
        response.sendRedirect(request.getHeader("referer"));
    }
}
