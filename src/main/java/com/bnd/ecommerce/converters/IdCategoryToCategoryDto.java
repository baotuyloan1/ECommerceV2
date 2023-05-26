package com.bnd.ecommerce.converters;

import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdCategoryToCategoryDto implements Converter<String, CategoryDto> {

  private final CategoryService categoryService;

  public IdCategoryToCategoryDto(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public CategoryDto convert(String id) {
    return categoryService.findCategoryDtoById(Integer.parseInt(id));
  }
}
