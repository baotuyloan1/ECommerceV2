package com.bnd.ecommerce.assembler;

import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.restcontroller.CategoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<CategoryDto, EntityModel<CategoryDto>> {
    @Override
    public EntityModel<CategoryDto> toModel(CategoryDto entity) {
        EntityModel<CategoryDto> entityModel = EntityModel.of(entity);
        entityModel.add(linkTo(methodOn(CategoryRestController.class).getOne(entity.getId())).withSelfRel().withType("GET"));
        entityModel.add(linkTo(methodOn(CategoryRestController.class).listAll()).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        return entityModel;
    }


}
