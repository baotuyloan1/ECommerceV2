package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.CustomerModelAssembler;
import com.bnd.ecommerce.dto.CustomerDto;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.jwt.JwtTokenProvider;
import com.bnd.ecommerce.service.CustomerService;
import com.bnd.ecommerce.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

  @PostMapping("/signup")
  public ResponseEntity<?> loginCustomer(
      @Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

//    Authentication authentication =
//        authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                customerDto.getEmail(), customerDto.getPassword()));
    Employee employee = employeeService.findByEmail(customerDto.getEmail());
    String jwt = jwtTokenProvider.generateToken(employee);

    return ResponseEntity.ok(jwt);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<CustomerDto>> getInfo(@PathVariable("id") long id) {
    return ResponseEntity.ok(customerModelAssembler.toModel(customerService.findById(id)));
  }
}
