package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.CustomerModelAssembler;
import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.jwt.JwtTokenProvider;
import com.bnd.ecommerce.security.customer.CustomerDetails;
import com.bnd.ecommerce.service.CustomerService;
import com.bnd.ecommerce.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

  private final CustomerModelAssembler customerModelAssembler;

  private final CustomerService customerService;

  private final EmployeeService employeeService;

  private final JwtTokenProvider jwtTokenProvider;

  public CustomerRestController(
      CustomerModelAssembler customerModelAssembler,
      CustomerService customerService,
      EmployeeService employeeService,
      JwtTokenProvider jwtTokenProvider) {
    this.customerModelAssembler = customerModelAssembler;
    this.customerService = customerService;
    this.employeeService = employeeService;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> loginCustomer(
      @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    CustomerDto foundCustomerDto = customerService.handleLoginCustomer(customerDto);
    String jwt = jwtTokenProvider.generateToken(foundCustomerDto);
    return ResponseEntity.ok(jwt);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> singupCustomer(
      @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    CustomerDto savedCustomerDto = customerService.save(customerDto);
    String jwt = jwtTokenProvider.generateToken(savedCustomerDto);
    return ResponseEntity.ok(jwt);
  }

  @GetMapping("/getInfo")
  public ResponseEntity<?> getInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.getPrincipal() instanceof CustomerDetails customerDetails) {
      EntityModel<CustomerDto> entityModel =
          customerModelAssembler.toModel(customerDetails.getCustomerDto());
      return ResponseEntity.ok(entityModel);
    }
    return ResponseEntity.badRequest().body("Invalid token");
  }
}
