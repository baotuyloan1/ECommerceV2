package com.bnd.ecommerce.controller.raw;

import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.exception.DeleteFailException;
import com.bnd.ecommerce.exception.UpdateFailException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawUI/categories")
public class CategoryController {

  private static final String REDIRECT_CATEGORIES = "redirect:/rawUI/categories/";
  private final CategoryService categoryService;

  private final MapStructMapper mapStructMapper;

  public CategoryController(CategoryService categoryService, MapStructMapper mapStructMapper) {
    this.categoryService = categoryService;
    this.mapStructMapper = mapStructMapper;
  }

  @GetMapping("/")
  public String showCategories(
      @RequestParam(value = "numPage", defaultValue = "1") int numPage,
      @RequestParam(value = "numberItems", defaultValue = "20") int numberItems,
      @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      Model model) {
    Page<Category> categories = categoryService.listAll(numPage, sortField, sortDir, numberItems);
    model.addAttribute("listCategories", categories.getContent());
    model.addAttribute("totalItems", categories.getTotalElements());
    model.addAttribute("totalPages", categories.getTotalPages());
    return "rawUI/category/categories";
  }

  @GetMapping("/createCategory")
  public String showCreateCategory(Model model) {
    Category category = new Category();
    List<CategoryDto> categoryDtoList = new ArrayList<>();
    List<Category> rootCategoryList = categoryService.getRootCategoryList();
    for (Category item : rootCategoryList) {
      categoryService.getLevelCategory(item, 0, categoryDtoList);
    }
    model.addAttribute("category", category);
    model.addAttribute("categoryDtoList", categoryDtoList);
    return "rawUI/category/new_category";
  }



  @PostMapping("/saveCategory")
  public String saveCategory(@Valid @ModelAttribute("category") Category category) {
    categoryService.saveCategory(category);
    return REDIRECT_CATEGORIES;
  }

  @GetMapping("/deleteCategory/{id}")
  public String deleteCategory(@PathVariable("id") int id) {
    boolean isDeleted = categoryService.deleteById(id);
    if (isDeleted) {
      return REDIRECT_CATEGORIES;
    }
    throw new DeleteFailException("Delete Category Fail");
  }

  @GetMapping("/editCategory/{id}")
  public String showEditCategory(@PathVariable("id") int id, Model model) {
    Category category = categoryService.findById(id);
    model.addAttribute("category", category);
    model.addAttribute("categories", categoryService.listCategories());
    return "rawUI/category/edit_category";
  }

  @PostMapping("/updateCategory/{id}")
  public String updateCategories(
      @PathVariable("id") int id, @Valid @ModelAttribute("category") Category category) {
    category.setId(id);
    Category updatedCategory = categoryService.saveCategory(category);
    if (updatedCategory != null) {
      return REDIRECT_CATEGORIES;
    } else {
      throw new UpdateFailException("Update failed");
    }
  }
}
