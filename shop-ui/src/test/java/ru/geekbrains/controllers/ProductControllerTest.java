package ru.geekbrains.controllers;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.data.ShortLineItem;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.services.CartService;
import ru.geekbrains.services.CategoryService;
import ru.geekbrains.services.ProductServiceForShop;


import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductServiceForShop productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EurekaClient eurekaClient;

    @BeforeEach
    public void init(){
        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl()).thenReturn("mock-homepage-url");

        when(eurekaClient.getNextServerFromEureka(anyString(),anyBoolean()))
                .thenReturn(instanceInfo);

    }

    @Test
    public void testShowDetail() throws Exception{

        Product product = saveCategoryAndProduct();

        mvc.perform(get("/products/details/"+product.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("shop-detail"))
                .andExpect(model().attributeExists("productData"))
                .andExpect(model().attributeExists("lineItem"))
                .andExpect(model().attribute("productData", new BaseMatcher<Product>() {
                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof ProductData){
                            ProductData productData = (ProductData) o;
                            return productData.getId().equals(product.getId());

                        }
                        return false;
                    }

                    @Override
                    public void describeTo(Description description) {

                    }
                }));
    }

    @Test
    public void testAddToCartFromDetail() throws Exception{

        Product product = saveCategoryAndProduct();

        List<LineItem> lineItems = cartService.getLineItems();
        assert lineItems.isEmpty();

        mvc.perform(  post("/products/details")
                .param("productId", product.getId().toString())
                .param("qty", "2")
        )
                .andDo(print())
                .andExpect( redirectedUrl("/products"))
                .andReturn();



//        lineItems = cartService.getLineItems();
//        assert !lineItems.isEmpty();
//        assert lineItems.get(0).equals(2);

    }


    private Product saveCategoryAndProduct(){

        Category category = categoryRepository.save(new Category("Category")) ;

        return productService.save(new Product("Product_title",
                                                                BigDecimal.valueOf(12.1),
                                                                category));

    }

}
