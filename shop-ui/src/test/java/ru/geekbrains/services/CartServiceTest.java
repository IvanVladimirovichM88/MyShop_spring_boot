package ru.geekbrains.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.impl.CartServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init(){
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart(){
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.valueOf(0.0), cartService.getTotalPrice());
    }

    @Test
    public void testAddOneProduct(){

        ProductData expectedProduct = createDefaultProductData();

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

    @Test
    public void testAddOneProductWithManyRepeat(){

        Integer number = 3;

        List<ProductData> expProducts = createSameDefaultProductData(number);

        for (ProductData prod : expProducts){
            cartService.addOneAndUpdate(prod);
        }


        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(3,lineItems.size());


        for (int i=0; i<number; i++){
            LineItem lineItem = lineItems.get(i);
            assertEquals(1, lineItem.getQty());

            assertNotNull(lineItem.getProductData());
            assertEquals(expProducts.get(i).getId(), lineItem.getProductData().getId());
            assertEquals(expProducts.get(i).getTitle(), lineItem.getProductData().getTitle());
        }


    }

    @Test
    public void testUpdateOneProduct(){

        ProductData expectedProduct = createDefaultProductData();

        cartService.addOneAndUpdate(expectedProduct);
        cartService.addOneAndUpdate(expectedProduct);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1,lineItems.size());


        LineItem lineItem = lineItems.get(0);
        assertEquals(2, lineItem.getQty());

        assertNotNull(lineItem.getProductData());
        assertEquals(expectedProduct.getId(), lineItem.getProductData().getId());
        assertEquals(expectedProduct.getTitle(), lineItem.getProductData().getTitle());

    }

    @Test
    public void testAddSeveralProduct(){

        ProductData expectedProduct = createDefaultProductData();
        Integer qty = 4;

        cartService.addSeveralAndUpdate(expectedProduct, qty);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1,lineItems.size());


        LineItem lineItem = lineItems.get(0);
        assertEquals(4, lineItem.getQty());

        assertNotNull(lineItem.getProductData());
        assertEquals(expectedProduct.getId(), lineItem.getProductData().getId());
        assertEquals(expectedProduct.getTitle(), lineItem.getProductData().getTitle());

    }

    @Test
    public void testAddSeveralAndUpdateProduct(){

        ProductData expectedProduct = createDefaultProductData();
        Integer qty = 4;

        cartService.addOneAndUpdate(expectedProduct);
        cartService.addSeveralAndUpdate(expectedProduct, qty);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1,lineItems.size());


        LineItem lineItem = lineItems.get(0);
        assertEquals(5, lineItem.getQty());

        assertNotNull(lineItem.getProductData());
        assertEquals(expectedProduct.getId(), lineItem.getProductData().getId());
        assertEquals(expectedProduct.getTitle(), lineItem.getProductData().getTitle());

    }

    @Test
    public void testRemoveAll(){

        Integer number = 3;

        List<ProductData> expProducts = createSameDefaultProductData(number);

        for (ProductData prod : expProducts){
            cartService.addOneAndUpdate(prod);
        }


        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(3,lineItems.size());

        cartService.removeAll(expProducts.get(1).getId());
        cartService.removeAll(expProducts.get(0).getId());

        List<LineItem> lineItemsAfterRemove = cartService.getLineItems();
        assertNotNull(lineItemsAfterRemove);
        assertEquals(1,lineItems.size());



    }

    private ProductData createDefaultProductData(){

        ProductData expectedProduct = new ProductData();
        expectedProduct.setId(1L);
        expectedProduct.setPrice( new BigDecimal("123.4"));
        expectedProduct.setTitle("product name");

        return expectedProduct;
    }

    private List<ProductData> createSameDefaultProductData( Integer num ){
        List<ProductData> products = new ArrayList<>();

        for(int i = 0; i<num; i++){
            ProductData product = new ProductData();
            product.setId( (long)i + 1 );
            product.setPrice( BigDecimal.valueOf( (i+1.0)*10.0) );
            product.setTitle("product name " + i);
            products.add(product);
        }

        return  products;
    }



}
