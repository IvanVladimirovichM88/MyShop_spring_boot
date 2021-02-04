package ru.geekbrains.services;

import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void addOneAndUpdate(ProductData productData);
    void addSeveralAndUpdate(ProductData productData, Integer quantity);
    void removeOneAndUpdate(Long productId);
    void removeAll(Long productId);
    void clearCart();
    List<LineItem> getLineItems();
    BigDecimal getTotalPrice();

}
