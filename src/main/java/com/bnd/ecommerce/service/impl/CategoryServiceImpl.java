package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.CategoryRepository;
import com.bnd.ecommerce.service.CategoryService;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final MapStructMapper mapStructMapper;

  public CategoryServiceImpl(
      CategoryRepository categoryRepository, MapStructMapper mapStructMapper) {
    this.categoryRepository = categoryRepository;
    this.mapStructMapper = mapStructMapper;
  }

  @Override
  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public CategoryDto saveCategory(CategoryDto categoryDto) {
    Category category = mapStructMapper.categoryDtoToCategory(categoryDto);
    return mapStructMapper.categoryToCategoryDto(categoryRepository.save(category));
  }

  @Override
  public Category findById(int id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      return category.get();
    } else {
      throw new ResourceNotFoundException("Category not found");
    }
  }

  @Override
  public List<Category> listCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public boolean deleteById(int id) {
    categoryRepository.deleteById(id);
    boolean isDeleted = categoryRepository.existsById(id);
    return !isDeleted;
  }

  public Page<Category> listAll(int pageNum, String sortField, String sortDir, int size) {
    Pageable pageable =
        PageRequest.of(
            pageNum - 1,
            size,
            sortDir.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
    return categoryRepository.findAll(pageable);
  }

  @Override
  public Set<CategoryDto> categoryDtoSet() {
    List<Category> categoryList = categoryRepository.findAll();
    return mapStructMapper.categorySetToCategoryDtoSet(new LinkedHashSet<>(categoryList));
  }

  @Override
  public CategoryDto findCategoryDtoById(int id) {
    Optional<Category> category = categoryRepository.findById(id);
    if (category.isPresent()) {
      return mapStructMapper.categoryToCategoryDto(category.get());
    }
    throw new ResourceNotFoundException("Category not found");
  }

  @Override
  public Category getParentCategoryByCategoryId(int id) {
    return categoryRepository.getParentCategoryByCategoryId(id);
  }

  @Override
  public List<Category> getRootCategoryList() {
    return categoryRepository.rootCategoryList();
  }

  @Override
  public List<CategoryDto> getRootCategoryDtoList() {
    List<CategoryDto> categoryDtoList = new ArrayList<>();
    for (Category category : categoryRepository.rootCategoryList()) {
      categoryDtoList.add(mapStructMapper.categoryToCategoryDto(category));
    }
    return categoryDtoList;
  }

  public void getLevelCategory(Category category, int level, List<CategoryDto> categoryDtoList) {
    if (category != null) {
      CategoryDto categoryDto = mapStructMapper.categoryToCategoryDto(category);
      categoryDto.setLevel(level);
      categoryDtoList.add(categoryDto);
      for (Category chilCategory : category.getChildren()) {
        getLevelCategory(chilCategory, level + 1, categoryDtoList);
      }
    }
  }

  @Override
  public List<CategoryDto> findSubCategoriesById(Integer id) {
    Category parentCategory =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
    List<Category> categoryList = new ArrayList<>();
    findMainCategory(parentCategory, categoryList);
    List<CategoryDto> categoryDtoList = new ArrayList<>();
    for (Category category : categoryList) {
      categoryDtoList.add(mapStructMapper.categoryToCategoryDto(category));
    }
    return categoryDtoList;
  }

  public void findMainCategory(Category categoryParent, List<Category> categoryMainList) {

    for (Category categoryChild : categoryParent.getChildren()) {
      if (categoryChild.getChildren().isEmpty()) {
        categoryMainList.add(categoryChild);
      } else {
        findMainCategory(categoryChild, categoryMainList);
      }
    }
  }
}
