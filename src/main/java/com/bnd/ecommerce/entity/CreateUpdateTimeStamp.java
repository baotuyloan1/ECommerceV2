package com.bnd.ecommerce.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@MappedSuperclass
public class CreateUpdateTimeStamp {

    private Timestamp createTime;
    private Timestamp updateTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @PrePersist
    public void onCreate() {
        this.createTime = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    public void onUpdate() {
        updateTime = new Timestamp(new Date().getTime());
    }
}
