package com.bnd.ecommerce.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderDto {

    private long id;



    @JsonProperty("address_id")
    private long customerAdress;

    @JsonProperty("products")
    private List<OrderDetailDto> products;

    @JsonProperty("total_price")
    private float totalPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getCustomerAdress() {
        return customerAdress;
    }

    public void setCustomerAdress(long customerAdress) {
        this.customerAdress = customerAdress;
    }

    public List<OrderDetailDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderDetailDto> products) {
        this.products = products;
    }
}
