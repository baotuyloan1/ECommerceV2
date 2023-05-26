package com.bnd.ecommerce.entity;

import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.enums.LogTypeProduct;

import javax.persistence.*;

@Entity
@Table(name = "product_log")
public class ProductLog extends CreateTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Product product;

    //    Số sản phẩm trước khi sửa là 10, sau khi sửa là 20 thì quantityChanged= 10, nếu giảm thì quantityChanged sẽ là số âm
    private int quantityChanged;
    @ManyToOne
    @JoinColumn(name = "changed_by")
    private Employee employee;


    private String messageLog;

    @Enumerated(EnumType.STRING)
    private LogTypeProduct logTypeProduct;

    public String getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(String messageLog) {
        this.messageLog = messageLog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantityChanged() {
        return quantityChanged;
    }

    public void setQuantityChanged(int quantityChanged) {
        this.quantityChanged = quantityChanged;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
