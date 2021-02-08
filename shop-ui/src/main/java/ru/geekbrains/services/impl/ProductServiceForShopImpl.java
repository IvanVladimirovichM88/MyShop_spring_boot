package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.ProductServiceForShop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceForShopImpl implements ProductServiceForShop {

    private ProductRepository productRepository;

    public ProductServiceForShopImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductData> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductData::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductData findProductDataById(Long id) {
        return new ProductData(productRepository.getOne(id)) ;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
