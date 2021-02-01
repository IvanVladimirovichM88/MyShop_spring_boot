package ru.geekbrains.services;

import org.junit.Before;
import org.junit.Test;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.services.impl.ProductServiceForShopImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductServiceTest {

    private ProductServiceForShop productService;
    private ProductRepository productRepository;

    @Before
    public void init(){
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceForShopImpl(productRepository );
    }

    @Test
    public void testFindById(){
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("category name");

        List<Category> categories = new ArrayList<>();
        categories.add(expectedCategory);

        Product expectedProduct = new Product();
        expectedProduct.setCategories(categories);
        expectedProduct.setPrice(1.1);
        expectedProduct.setDescription("product description");
        expectedProduct.setTitle("product title");
        expectedProduct.setId(1L);
        expectedProduct.setPictureRefs(new ArrayList<>());

        when(productRepository.getOne(eq(1L)))
                .thenReturn(expectedProduct);

        ProductData productData = productService.findProductDataById(1L);

        assertNotNull(productData);
        assertEquals(expectedProduct.getId(), productData.getId());
        assertEquals(expectedProduct.getTitle(), productData.getTitle());

    }

}
