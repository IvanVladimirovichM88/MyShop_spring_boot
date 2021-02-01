package ru.geekbrains.controllers;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.persists.entities.Category;
import ru.geekbrains.persists.entities.Product;
import ru.geekbrains.persists.repositories.CategoryRepository;
import ru.geekbrains.persists.repositories.ProductRepository;
import ru.geekbrains.services.CategoryService;
import ru.geekbrains.services.ProductServiceForShop;


import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void testProductDetail() throws Exception{

        Category category = categoryRepository.save(new Category("Category")) ;

        Product product = productRepository.save(new Product("Product_title", 12.1, category));

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

}
