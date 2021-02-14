package ru.geekbrains.data;

public class ShortLineItem {
    private Long productId;
    private Integer qty;

    public ShortLineItem() {
    }

    public ShortLineItem(Long productId, Integer qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
