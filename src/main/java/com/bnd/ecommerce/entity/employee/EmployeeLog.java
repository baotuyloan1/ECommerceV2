package com.bnd.ecommerce.entity.employee;

import com.bnd.ecommerce.entity.CreateTimestamp;
import com.bnd.ecommerce.enums.LogTypeEmployee;

import javax.persistence.*;

@Entity
@Table(name = "employee_log")
public class EmployeeLog extends CreateTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String messageLog;

    @Enumerated(EnumType.STRING)
    private LogTypeEmployee logTypeEmployee;



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

    public LogTypeEmployee getLogTypeEmployee() {
        return logTypeEmployee;
    }

    public void setLogTypeEmployee(LogTypeEmployee logTypeEmployee) {
        this.logTypeEmployee = logTypeEmployee;
    }


}
