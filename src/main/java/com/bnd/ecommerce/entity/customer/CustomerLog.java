package com.bnd.ecommerce.entity.customer;

import com.bnd.ecommerce.entity.CreateTimestamp;
import com.bnd.ecommerce.enums.LogTypeCustomer;

import javax.persistence.*;

@Entity
@Table(name = "customer_log")
public class CustomerLog extends CreateTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Enumerated(EnumType.STRING)
    private LogTypeCustomer logTypeCustomer;

    private String messageLog;


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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LogTypeCustomer getLogTypeCustomer() {
        return logTypeCustomer;
    }

    public void setLogTypeCustomer(LogTypeCustomer logTypeCustomer) {
        this.logTypeCustomer = logTypeCustomer;
    }


}
