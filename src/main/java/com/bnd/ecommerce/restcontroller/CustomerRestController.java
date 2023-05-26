package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.CustomerModelAssembler;
import com.bnd.ecommerce.entity.customer.Customer;
import com.bnd.ecommerce.service.CustomerService;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

  private final CustomerModelAssembler customerModelAssembler;

  private final CustomerService customerService;

  public CustomerRestController(
      CustomerModelAssembler customerModelAssembler, CustomerService customerService) {
    this.customerModelAssembler = customerModelAssembler;
    this.customerService = customerService;
  }

  @SuppressWarnings("rawtypes")
  @PostMapping("/customers")
  ResponseEntity newCustomer(
      @Valid @RequestBody Customer customer, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    EntityModel<Customer> customerEntityModel =
        customerModelAssembler.toModel(customerService.save(customer));
    return ResponseEntity.created(
            customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(customerEntityModel);
  }




}
