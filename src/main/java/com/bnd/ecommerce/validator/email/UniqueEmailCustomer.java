package com.bnd.ecommerce.validator.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCustomerEmailValidator.class)
public @interface UniqueEmailCustomer {
  String message() default "Default: Email is exist in system";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
