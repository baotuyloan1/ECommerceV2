package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.CategoryModelAssembler;
import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.service.CategoryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/categories")
@Validated
public class CategoryRestController {

    private final CategoryService categoryService;
    private final CategoryModelAssembler categoryModelAssembler;

    public CategoryRestController(CategoryService categoryService, CategoryModelAssembler categoryModelAssembler) {
        this.categoryService = categoryService;
        this.categoryModelAssembler = categoryModelAssembler;
    }

    @GetMapping("/{id}")
    public Category getOne(@Positive(message = "Category ID must be greater than zero") @PathVariable("id") int id) {
        return categoryService.findById(id);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<CategoryDto>> listAll() {
        Set<CategoryDto> categoryDtoSet = categoryService.categoryDtoSet();
        Set<EntityModel<CategoryDto>> categoryEntityModelList = new LinkedHashSet<>();
        for (CategoryDto categoryDto : categoryDtoSet) {
            EntityModel<CategoryDto> model = categoryModelAssembler.toModel(categoryDto);
            categoryEntityModelList.add(model);
        }
        CollectionModel<EntityModel<CategoryDto>> categoryCollectionModel = CollectionModel.of(categoryEntityModelList);
        return categoryCollectionModel.add(linkTo(methodOn(CategoryRestController.class).listAll()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<CategoryDto>> addOne(@RequestBody @Valid CategoryDto categoryDto) {
        EntityModel<CategoryDto> savedCategory = categoryModelAssembler.toModel(categoryService.saveCategory(categoryDto));
        return ResponseEntity.created(URI.create("/api/categories/" + categoryDto.getId())).body(savedCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Positive(message = "Deleted Category ID must be greater than zero") Integer id) {
        categoryService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestBody @Valid CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
    }
}
