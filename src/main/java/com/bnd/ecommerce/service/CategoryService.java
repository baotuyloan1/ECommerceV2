package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.entity.Category;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;

public interface CategoryService {

  Category saveCategory(Category category);
  CategoryDto saveCategory(CategoryDto categoryDto);

  Category findById(int id);

  List<Category> listCategories();

  Set<CategoryDto> categoryDtoSet();

  boolean deleteById(int id);

  Page<Category> listAll(int pageNum, String sortField, String sortDir, int size);


  CategoryDto findCategoryDtoById(int id);

  Category getParentCategoryByCategoryId(int id);

  List<Category> getRootCategoryList();

  void getLevelCategory(Category category, int level, List<CategoryDto> categoryDtoList);
}
