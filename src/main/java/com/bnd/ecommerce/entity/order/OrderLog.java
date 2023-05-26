package com.bnd.ecommerce.entity.order;

import com.bnd.ecommerce.entity.CreateTimestamp;
import com.bnd.ecommerce.enums.LogTypeOrder;

import javax.persistence.*;

@Entity
@Table(name = "order_log")
public class OrderLog extends CreateTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String messageLog;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @Enumerated(EnumType.STRING)
    private LogTypeOrder logTypeOrder;


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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LogTypeOrder getLogTypeOrder() {
        return logTypeOrder;
    }

    public void setLogTypeOrder(LogTypeOrder logTypeOrder) {
        this.logTypeOrder = logTypeOrder;
    }
}
