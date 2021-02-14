package ru.geekbrains.data;

import liquibase.pro.packaged.B;

import java.math.BigDecimal;

public class LineItem {

    private ProductData productData;
    private Integer qty;

    public LineItem() {
    }

    public LineItem(ProductData productData) {
        this.productData = productData;
        this.qty = 1;
    }

    public LineItem(ProductData productData, Integer qty) {
        this.productData = productData;
        this.qty = qty;
    }

    public Long getPictureIdForCart(){
        return this.productData.getPictureId();
    }

    public String getProductTitle(){
        return this.productData.getTitle();
    }

    public BigDecimal getTotalPrice(){
        return productData.getPrice().multiply(BigDecimal.valueOf(qty)) ;
    }


    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
