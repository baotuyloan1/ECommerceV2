package com.bnd.ecommerce.entity.order;

import com.bnd.ecommerce.entity.CreateTimestamp;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.enums.LogTypeOrderDetail;

import javax.persistence.*;

@Entity
@Table(name = "order_detail_log")
public class OrderDetailLog extends CreateTimestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    private String messageLog;


    private int quantityChanged;

    @ManyToOne
    @JoinColumn(name = "changed_by_employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "changed_by_customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private LogTypeOrderDetail logTypeOrderDetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(String messageLog) {
        this.messageLog = messageLog;
    }

    public int getQuantityChanged() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LogTypeOrderDetail getLogTypeOrderDetail() {
        return logTypeOrderDetail;
    }

    public void setLogTypeOrderDetail(LogTypeOrderDetail logTypeOrderDetail) {
        this.logTypeOrderDetail = logTypeOrderDetail;
    }
}
