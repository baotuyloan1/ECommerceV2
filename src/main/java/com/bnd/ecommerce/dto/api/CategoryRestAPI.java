package com.bnd.ecommerce.dto.api;

import com.bnd.ecommerce.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryRestAPI {

  @JsonIgnore private Category category;

  private String name;
  private long id;

  private String desctiption;

  public String getDesctiption() {
    return category.getDescription();
  }

  public void setDesctiption(String desctiption) {
    this.desctiption = desctiption;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return category.getName();
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return category.getId();
  }

  public void setId(long id) {
    this.id = id;
  }

  public CategoryRestAPI(Category category) {
    this.category = category;
    this.desctiption = category.getDescription();
    this.name = category.getName();
    this.id = category.getId();
  }
}
