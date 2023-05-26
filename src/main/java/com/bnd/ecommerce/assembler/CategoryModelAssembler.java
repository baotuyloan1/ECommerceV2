package com.bnd.ecommerce.assembler;

import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.restcontroller.CategoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>> {
    @Override
    public EntityModel<Category> toModel(Category entity) {
        EntityModel<Category> entityModel = EntityModel.of(entity);
        entityModel.add(linkTo(methodOn(CategoryRestController.class).getOne(entity.getId())).withSelfRel().withType("GET"));
        entityModel.add(linkTo(methodOn(CategoryRestController.class).listAll()).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        return entityModel;
    }


}
