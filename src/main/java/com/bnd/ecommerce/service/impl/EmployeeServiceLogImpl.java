package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.entity.employee.EmployeeLog;
import com.bnd.ecommerce.repository.EmployeeLogRepository;
import com.bnd.ecommerce.repository.EmployeeRepository;
import com.bnd.ecommerce.service.EmployeeServiceLog;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceLogImpl implements EmployeeServiceLog {

    private final EmployeeLogRepository employeeLogRepository;

    public EmployeeServiceLogImpl(EmployeeLogRepository employeeLogRepository){
        this.employeeLogRepository = employeeLogRepository;
    }
    @Override
    public EmployeeLog save(EmployeeLog employeeLog) {
        return employeeLogRepository.save(employeeLog);
    }
}
