package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Relation(collectionRelation = "categories")
public class CategoryDto extends CreateUpdateTimeStamp {

    private int id;

    @NotBlank(message = "Category cannot be blank")
    @Length(min = 5, max = 200, message = "Product name must be between 5-512 characters")
    private String name;

    private String description;

    @JsonIgnore
    private CategoryDto parentCategoryDto;

    private Set<CategoryDto> childrenDtoSet;

    //    @JsonIgnore
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


    public CategoryDto getParentCategoryDto() {
        return parentCategoryDto;
    }

    public void setParentCategoryDto(CategoryDto parentCategoryDto) {
        this.parentCategoryDto = parentCategoryDto;
    }

    public Set<CategoryDto> getChildrenDtoSet() {
        return childrenDtoSet;
    }

    public void setChildrenDtoSet(Set<CategoryDto> childrenDtoSet) {
        this.childrenDtoSet = childrenDtoSet;
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

    @Override
    public String toString() {
        return "{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
