package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.CustomerModelAssembler;
import com.bnd.ecommerce.dto.CustomerAddressDto;
import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.jwt.JwtTokenProvider;
import com.bnd.ecommerce.security.customer.CustomerDetails;
import com.bnd.ecommerce.service.CustomerAddressService;
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
  private final CustomerAddressService customerAddressService;

  public CustomerRestController(
      CustomerModelAssembler customerModelAssembler,
      CustomerService customerService,
      EmployeeService employeeService,
      JwtTokenProvider jwtTokenProvider,
      CustomerAddressService customerAddressService) {
    this.customerModelAssembler = customerModelAssembler;
    this.customerService = customerService;
    this.employeeService = employeeService;
    this.jwtTokenProvider = jwtTokenProvider;
    this.customerAddressService = customerAddressService;
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

  @PostMapping("/newAddress")
  public ResponseEntity<?> postAddress(
      @Valid @RequestBody CustomerAddressDto customerAddressDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.getPrincipal() instanceof CustomerDetails customerDetails) {
      customerAddressDto.setCustomerDto(customerDetails.getCustomerDto());
      CustomerAddressDto savedCustomerAddressDto = customerAddressService.save(customerAddressDto);
      return ResponseEntity.ok(savedCustomerAddressDto);
    } else return ResponseEntity.badRequest().body("Invalid token");
  }


}
