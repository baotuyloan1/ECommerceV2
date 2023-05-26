package com.bnd.ecommerce.assembler;

import com.bnd.ecommerce.entity.customer.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler
    implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
  @Override
  public EntityModel<Customer> toModel(Customer entity) {
    EntityModel<Customer> customerEntityModel = EntityModel.of(entity);
    //        customerEntityModel.add(linkTo(methodOn(CustomerRestController.class)));
    return customerEntityModel;
  }
}
