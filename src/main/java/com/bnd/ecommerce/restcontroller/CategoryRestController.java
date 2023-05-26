package com.bnd.ecommerce.restcontroller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.bnd.ecommerce.assembler.CategoryModelAssembler;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class CategoryRestController {

  private final CategoryService categoryService;
  private final CategoryModelAssembler categoryModelAssembler;

  public CategoryRestController(
      CategoryService categoryService, CategoryModelAssembler categoryModelAssembler) {
    this.categoryService = categoryService;
    this.categoryModelAssembler = categoryModelAssembler;
  }

  @GetMapping("/categories/{id}")
  public Category getOne(
      @Positive(message = "Category ID must be greater than zero") @PathVariable("id") int id) {
    return categoryService.findById(id);
  }

  @GetMapping("/categories")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public CollectionModel<EntityModel<Category>> listAll() {
    List<Category> categoryList = categoryService.listCategories();
    List<EntityModel<Category>> categoryEntityModelList =
            new ArrayList<>();
    for (Category category : categoryList) {
      EntityModel<Category> model = categoryModelAssembler.toModel(category);
      categoryEntityModelList.add(model);
    }
    CollectionModel<EntityModel<Category>> categoryCollectionModel =
        CollectionModel.of(categoryEntityModelList);
    return categoryCollectionModel.add(
        linkTo(methodOn(CategoryRestController.class).listAll()).withSelfRel());
  }

  @PostMapping("/categories")
  public Category addOne(@RequestBody @Valid Category category) {
    return categoryService.saveCategory(category);
  }

  @DeleteMapping("/categories/{id}")
  public void delete(
      @PathVariable("id") @Positive(message = "Deleted Category ID must be greater than zero")
          Integer id) {
    categoryService.deleteById(id);
  }
}
