package com.bnd.ecommerce.validator.product;

import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.service.ProductService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {

  private final ProductService productService;

  public UniqueProductNameValidator(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public boolean isValid(String name, ConstraintValidatorContext context) {
    if (name == null) return false;
    Product product = productService.findByName(name);
    if (product != null) {
      context
          .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
          .addConstraintViolation();
      return false;
    }
    return true;
  }
}
