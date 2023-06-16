package com.bnd.ecommerce.dto.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDetailDto {
    private long productId;

    private int quantity;

    @JsonProperty("unit_price")
    private float price;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
