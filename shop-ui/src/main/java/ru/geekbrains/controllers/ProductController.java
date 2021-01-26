package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.data.ShortLineItem;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.ProductServiceForShop;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductServiceForShop productService;
    private CartService cartService;

    public ProductController(ProductServiceForShop productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping
    public String showAllProducts(
            Model model
    ){
        model.addAttribute("productsData", productService.findAllProducts());
        List<LineItem> allLineItems = cartService.getLineItems();
        model.addAttribute("allLineItems",allLineItems);
        model.addAttribute("uniqProductInCart", allLineItems.size());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "shop";
    }

    @GetMapping("/details/{productId}")
    public String showDetails(
            Model model,
            @PathVariable(name = "productId") Long productId
    ){
        List<LineItem> allLineItems = cartService.getLineItems();
        model.addAttribute("allLineItems",allLineItems);
        model.addAttribute("uniqProductInCart", allLineItems.size());
        model.addAttribute("totalPrice", cartService.getTotalPrice());

        model.addAttribute("productData", productService.findProductDataById(productId));
        model.addAttribute("lineItem", new ShortLineItem(productId,0));

        return "shop-detail";
    }



    @PostMapping(value = "/details")
    public String addToCartFormDetail(
            @ModelAttribute ShortLineItem lineItem
    ){
        ProductData productData = productService.findProductDataById( lineItem.getProductId() );

        cartService.addSeveralAndUpdate(
                productService.findProductDataById( lineItem.getProductId() ),
                lineItem.getQty());
        return "redirect:/products";
    }

}
