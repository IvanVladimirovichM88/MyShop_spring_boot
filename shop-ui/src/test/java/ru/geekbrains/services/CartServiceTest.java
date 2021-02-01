package ru.geekbrains.services;


import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.impl.CartServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @Before
    public void init(){
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart(){
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(0.0, cartService.getTotalPrice());
    }

    @Test
    public void testAddOneProduct(){
        ProductData expectedProduct = new ProductData();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(123.1);
        expectedProduct.setTitle("product name");

        cartService.addOneAndUpdate(expectedProduct);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1,lineItems.size());


        LineItem lineItem = lineItems.get(0);
        assertEquals(1, lineItem.getQty());

        assertNotNull(lineItem.getProductData());
        assertEquals(expectedProduct.getId(), lineItem.getProductData().getId());
        assertEquals(expectedProduct.getTitle(), lineItem.getProductData().getTitle());

    }

}
