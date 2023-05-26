package com.bnd.ecommerce.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class CreateTimestamp {

    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @PrePersist
    public void onCreateTime() {
        this.createTime = new Timestamp(new Date().getTime());
    }
}
