package com.bnd.ecommerce.dto.api;

import com.bnd.ecommerce.entity.Product;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

public class OrderDetailDtoReponse {

    private Product product;
    private int quantity;
    private Timestamp createTime;
    private UpdateTimestamp updateTimestamp;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public UpdateTimestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(UpdateTimestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
