package ru.geekbrains.services;

import ru.geekbrains.data.ProductData;

import java.util.List;
import java.util.Optional;

public interface ProductServiceForShop {

    List<ProductData> findAllProducts();
    ProductData findProductDataById(Long id);

}
