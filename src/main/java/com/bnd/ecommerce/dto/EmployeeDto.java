package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.validator.email.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeDto {
  @JsonProperty("id")
  private long id;

  @NotBlank
  @Size(min = 3)
  @JsonProperty("first_name")
  private String firstName;

  @NotBlank
  @JsonProperty("last_name")
  @Size(min = 3)
  private String lastName;

  @JsonProperty("is_enabled")
  private boolean isEnabled;

  @NotBlank
  @Email
  @JsonProperty("email")
  @UniqueEmail(message = "Entity: Email exits in system")
  private String email;

  @NotBlank
  @JsonProperty("phone")
  private String phone;

  @JsonProperty("roles")
  @Size(min = 1, message = "User must at least 1 role")
  private Set<Role> roles = new HashSet<>();

  @NotBlank
  @JsonProperty("password")
  private String password;
}
