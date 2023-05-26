package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.validator.email.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeCreateDto extends CreateUpdateTimeStamp {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
