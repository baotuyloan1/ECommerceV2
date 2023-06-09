package com.bnd.ecommerce.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.restcontroller.CustomerRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler
    implements RepresentationModelAssembler<CustomerDto, EntityModel<CustomerDto>> {
  @Override
  public EntityModel<CustomerDto> toModel(CustomerDto entity) {
    EntityModel<CustomerDto> customerEntityModel = EntityModel.of(entity);
    customerEntityModel.add(
        linkTo(methodOn(CustomerRestController.class).getInfo()).withSelfRel().withType("GET"));
//    customerEntityModel.add(
//            linkTo(methodOn(CustomerRestController.class).getInfo(entity.getId())).withSelfRel().withType("GET"));
    return customerEntityModel;
  }
}
