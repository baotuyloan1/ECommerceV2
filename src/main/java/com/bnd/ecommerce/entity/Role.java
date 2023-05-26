package com.bnd.ecommerce.entity;

import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.validator.role.UniqueRoleName;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @UniqueRoleName(message = "Message: name của role không được trùng")
  private String name;

  private String description;

  @ManyToMany(mappedBy = "roles")
  private Set<Employee> employees;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String keyword) {
    this.description = keyword;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return name;
  }
}
