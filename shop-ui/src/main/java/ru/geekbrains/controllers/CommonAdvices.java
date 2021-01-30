package ru.geekbrains.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.services.CartService;

import java.util.List;

@ControllerAdvice
public class CommonAdvices {

    private static final Logger logger = LoggerFactory.getLogger(CommonAdvices.class);
    private final EurekaClient eurekaClient;
    private CartService cartService;

    public CommonAdvices(EurekaClient eurekaClient, CartService cartService) {
        this.eurekaClient = eurekaClient;
        this.cartService = cartService;
    }

    @ModelAttribute
    public void pictureServiceUrlAttribute(Model model){
        InstanceInfo server = eurekaClient.getNextServerFromEureka("GATEWAY-SERVICE", false);
        logger.info("Picture service instance: {}", server);
        model.addAttribute("pictureServiceUrl", server.getHomePageUrl() + "picture-service");

        // for cart
        List<LineItem> allLineItems = cartService.getLineItems();
        model.addAttribute("allLineItems",allLineItems);
        model.addAttribute("uniqProductInCart", allLineItems.size());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
    }
}
