package ru.geekbrains.services.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.data.LineItem;
import ru.geekbrains.data.ProductData;
import ru.geekbrains.services.CartService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    private List<LineItem> lineItems;
    private Double totalPrice;

    public CartServiceImpl() {
        lineItems = new ArrayList<>();
        totalPrice = 0.0;
    }


    @Override
    public void addOneAndUpdate(ProductData productData) {
        for (LineItem lineItem : lineItems){
            if(lineItem.getProductData().getId().equals(productData.getId())){
                lineItem.setQty(lineItem.getQty()+1);
                recalculate();
                return;
            }
        }
        lineItems.add(new LineItem(productData));
        recalculate();
    }

    @Override
    public void addSeveralAndUpdate(ProductData productData, Integer quantity) {
        for (LineItem lineItem : lineItems){
            if(lineItem.getProductData().getId().equals(productData.getId())){
                lineItem.setQty(lineItem.getQty() + quantity);
                recalculate();
                return;
            }
        }
        lineItems.add(new LineItem(productData,quantity));
        recalculate();
    }

    @Override
    public void removeOneAndUpdate(Long productId) {
        Iterator<LineItem> iterator = lineItems.iterator();
        while (iterator.hasNext()){
            LineItem lineItem = iterator.next();
            if(lineItem.getProductData().getId().equals(productId)){
                lineItem.setQty(lineItem.getQty() - 1);
                if (lineItem.getQty() == 0){
                    iterator.remove();
                }
                recalculate();
                return;
            }
        }

    }

    @Override
    public void removeAll(Long productId) {
        Iterator<LineItem> iterator = lineItems.iterator();
        while (iterator.hasNext()){
            LineItem lineItem = iterator.next();
            if(lineItem.getProductData().getId().equals(productId)){
                iterator.remove();
                recalculate();
                return;
            }
        }
    }

    @Override
    public void clearCart() {
        totalPrice = 0.0;
        lineItems = new ArrayList<>();

    }

    void recalculate(){
        totalPrice = lineItems.stream()
                .mapToDouble( lineItem -> lineItem.getQty()*lineItem.getProductData().getPrice() )
                .sum();
    }

    @Override
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
