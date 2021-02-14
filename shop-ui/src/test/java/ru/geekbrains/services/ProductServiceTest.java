package ru.geekbrains.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.services.impl.ProductServiceForShopImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceTest {

    private ProductServiceForShop productService;
    private ProductRepository productRepository;

    @BeforeEach
    public void init(){
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceForShopImpl(productRepository );
    }

    @Test
    public void testFindProductDataById(){

        Product expectedProduct =  createProduct(BigDecimal.valueOf(1.1),
                "product description",
                "product title",
                1L);

        when(productRepository.getOne(eq(1L)))
                .thenReturn(expectedProduct);

        ProductData productData = productService.findProductDataById(1L);

        assertNotNull(productData);
        assertEquals(expectedProduct.getId(), productData.getId());
        assertEquals(expectedProduct.getTitle(), productData.getTitle());

    }

    @Test
    public void testFindAllProducts(){

        Product expProduct1 =  createProduct(BigDecimal.valueOf(1.1),
                "product description 1",
                "product title 2",
                1L);

        Product expProduct2 = createProduct(BigDecimal.valueOf(2.1),
                "product description 2",
                "product title 2",
                2L);

        List<Product> products = new ArrayList<>();
        products.add(expProduct1);
        products.add(expProduct2);

        when(productRepository.findAll())
                .thenReturn(products);

        List<ProductData> productsData = productService.findAllProducts();

        assertNotNull(productsData);
        assertEquals(products.get(0).getId(), productsData.get(0).getId());
        assertEquals(products.get(0).getTitle(), productsData.get(0).getTitle());

        assertEquals(products.get(1).getId(), productsData.get(1).getId());
        assertEquals(products.get(1).getTitle(), productsData.get(1).getTitle());
    }


    private List<Category> createListCategory(){
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setTitle("category name");

        List<Category> categories = new ArrayList<>();
        categories.add(expectedCategory);
        return categories;
    }

    private Product createProduct(BigDecimal price,
                                 String description,
                                 String title,
                                 Long id
    ) {
        Product retProduct = new Product();
        retProduct.setCategories( createListCategory() );
        retProduct.setPrice( price );
        retProduct.setDescription(description);
        retProduct.setTitle(title);
        retProduct.setId(id);
        retProduct.setPictureRefs(new ArrayList<>());

        return  retProduct;
    }
}
