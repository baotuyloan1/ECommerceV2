package com.bnd.ecommerce.dto;

import javax.validation.constraints.Min;

public class QuantityDto {

    @Min(value = 1)
    long quantity;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
