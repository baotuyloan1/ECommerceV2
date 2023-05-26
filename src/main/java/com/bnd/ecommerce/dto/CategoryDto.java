package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategoryDto extends CreateUpdateTimeStamp {

  private int id;

  @NotBlank(message = "Category cannot be blank")
  @Length(min = 5, max = 200, message = "Product name must be between 5-512 characters")
  private String name;

  private String description;

  private Category parentCategory;

  private Set<Category> children;

  private int level;

  public int getLevel() {
    return level;
  }


  public void setLevel(int level) {
    this.level = level;
  }

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

  public void setDescription(String description) {
    this.description = description;
  }

  public Category getParentCategory() {
    return parentCategory;
  }

  public void setParentCategory(Category parentCategory) {
    this.parentCategory = parentCategory;
  }

  public Set<Category> getChildren() {
    return children;
  }

  public void setChildren(Set<Category> children) {
    this.children = children;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CategoryDto that = (CategoryDto) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
