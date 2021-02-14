package ru.geekbrains.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.UserData;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.UserServiceShopUI;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class CommonAdvices {

    private static final Logger logger = LoggerFactory.getLogger(CommonAdvices.class);
    private final EurekaClient eurekaClient;
    private CartService cartService;
    private UserServiceShopUI userService;

    public CommonAdvices(EurekaClient eurekaClient, CartService cartService, UserServiceShopUI userService) {
        this.eurekaClient = eurekaClient;
        this.cartService = cartService;
        this.userService = userService;
    }

    @ModelAttribute
    public void pictureServiceUrlAttribute(Model model, Principal principal){
        InstanceInfo server = eurekaClient.getNextServerFromEureka("GATEWAY-SERVICE", false);
        logger.info("Picture service instance: {}", server);
        model.addAttribute("pictureServiceUrl", server.getHomePageUrl() + "picture-service");

        // for cart
        List<LineItem> allLineItems = cartService.getLineItems();
        model.addAttribute("allLineItems",allLineItems);
        model.addAttribute("uniqProductInCart", allLineItems.size());
        model.addAttribute("totalPrice", cartService.getTotalPrice());

        // for user
        model.addAttribute("userData", userService.getCurrentUserData(principal).orElse(new UserData("Anonymous")) ) ;

    }
}
