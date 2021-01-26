package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.ProductServiceForShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        List<LineItem> allLineItems = cartService.getLineItems();
        model.addAttribute("allLineItems",allLineItems);
        model.addAttribute("uniqProductInCart", allLineItems.size());
        model.addAttribute("totalPrice", cartService.getTotalPrice());

        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return"cart";
    }

    @GetMapping("/add/{productId}")
    public void addProductInCart(
            @PathVariable (name = "productId") Long productId,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        ProductData productData = productService.findProductDataById(productId);
        cartService.addOneAndUpdate(productData);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/remove/{productId}")
    public String removeProductGroupInCart(
            @PathVariable(name = "productId") Long productId
    ){
        cartService.removeAll(productId);
        return "redirect:/cart";
    }


}
