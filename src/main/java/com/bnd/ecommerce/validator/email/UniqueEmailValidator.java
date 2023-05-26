package com.bnd.ecommerce.validator.email;

import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.repository.EmployeeRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final EmployeeRepository employeeRepository;

  public UniqueEmailValidator(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    Employee employee = employeeRepository.findByEmail(value);
    if (employee != null) {
      context
          .buildConstraintViolationWithTemplate(
              context.getDefaultConstraintMessageTemplate() // get message
              )
          .addConstraintViolation() // thêm lỗi vào context validation hiện tại
      ;
      return false;
    }
    return true;
  }
}
