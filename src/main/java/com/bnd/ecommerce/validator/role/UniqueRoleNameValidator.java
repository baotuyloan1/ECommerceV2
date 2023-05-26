package com.bnd.ecommerce.validator.role;

import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.service.RoleService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {

  private final RoleService roleService;

  UniqueRoleNameValidator(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public boolean isValid(String roleName, ConstraintValidatorContext context) {
    Role role = roleService.findByRoleName(roleName);
    if (role != null) {
      context
          .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
          .addConstraintViolation();
      return false;
    }
    return true;
  }
}
