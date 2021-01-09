package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public ProductData getProductDataById(Long id) {
        return new ProductData(productRepository.getOne(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductData> getAllProductsData() {
        return productRepository.findAll().stream().map(ProductData::new).collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product createOrUpdateProduct(ProductData productData) {
        Product product = productData.getId() == null ?
                            new Product() :
                            productRepository.getOne(productData.getId());

        product.setTitle(productData.getTitle());
        product.setPrice(productData.getPrice());
        product.setDescription(productData.getDescription());
        product.setCategories(productData.getCategoryTitle().stream()
                .map(categoryRepository::findByTitle)
                .collect(Collectors.toList()));

        return productRepository.save(product);
    }


}
