package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.CustomerModelAssembler;
import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.service.CustomerService;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

  private final CustomerModelAssembler customerModelAssembler;

  private final CustomerService customerService;

  public CustomerRestController(
      CustomerModelAssembler customerModelAssembler, CustomerService customerService) {
    this.customerModelAssembler = customerModelAssembler;
    this.customerService = customerService;
  }


  @SuppressWarnings("rawtypes")
  @PostMapping
  public ResponseEntity newCustomer(
      @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    EntityModel<CustomerDto> customerEntityModel =
        customerModelAssembler.toModel(customerService.save(customerDto));
    return ResponseEntity.created(
            customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(customerEntityModel);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<CustomerDto>> getInfo(@PathVariable("id") long id) {
    return ResponseEntity.ok(customerModelAssembler.toModel(customerService.findById(id)));
  }
}
