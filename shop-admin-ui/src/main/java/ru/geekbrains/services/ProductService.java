package ru.geekbrains.services;

import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.Product;

import java.util.List;

public interface ProductService {

    Product getById(Long id);
    ProductData getProductDataById(Long id);
    List<Product> getAllProducts();
    List<ProductData> getAllProductsData();
    void remove(Long id);
    void saveOrUpdate(Product product);
    Product createOrUpdateProduct(ProductData productData);

}
