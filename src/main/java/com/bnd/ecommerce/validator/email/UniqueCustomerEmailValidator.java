package com.bnd.ecommerce.validator.email;

import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.repository.CustomerRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueCustomerEmailValidator
    implements ConstraintValidator<UniqueEmailCustomer, String> {

  private final CustomerRepository customerRepository;

  public UniqueCustomerEmailValidator(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    Customer customerDto =
        customerRepository.findByEmail(value).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    if (customerDto != null) {
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
