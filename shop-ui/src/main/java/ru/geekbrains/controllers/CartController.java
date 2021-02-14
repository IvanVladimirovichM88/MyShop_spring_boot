package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.ProductServiceForShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private ProductServiceForShop productService;

    public CartController(CartService cartService, ProductServiceForShop productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String showCart(Model model){

        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return"cart";
    }


    @GetMapping("/remove/{productId}")
    public String removeProductGroupInCart(
            @PathVariable(name = "productId") Long productId
    ){
        cartService.removeAll(productId);
        return "redirect:/cart";
    }


}
