package com.bnd.ecommerce.controller.raw;

import com.bnd.ecommerce.dto.EmployeeCreateDto;
import com.bnd.ecommerce.dto.EmployeeUpdateDto;
import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.service.EmployeeService;
import com.bnd.ecommerce.service.RoleService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawUI/admin")
public class EmployeeController {
  private final EmployeeService employeeService;
  private final RoleService roleService;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

  private static final String REDIRECT_EMPLOYEES = "redirect:/rawUI/admin/employees/1";
  private static final String ALL_ROLES_STRING = "allRoles";

  public EmployeeController(EmployeeService employeeService, RoleService roleService) {
    this.employeeService = employeeService;
    this.roleService = roleService;
  }

  @GetMapping("/employees/{pageNum}")
  public String showEmployees(
      @RequestParam(value = "keyword", defaultValue = "") String keyword,
      @PathVariable("pageNum") int pageNum,
      @RequestParam(value = "itemsNumber", defaultValue = "5") int size,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
      Model model,
      Authentication authentication) {

    LOGGER.trace("for tracing purpose");
    LOGGER.debug("for debugging purpose");
    LOGGER.info("for informational purpose");
    LOGGER.warn("for warning purpose");
    LOGGER.error("for logging errors");

    Page<Employee> employeePage =
        employeeService.listAll(size, pageNum, sortField, sortDir, keyword);

    model.addAttribute("listEmployees", employeePage.getContent());
    Set<String> currentRoles = new HashSet<>();
    for (GrantedAuthority role : authentication.getAuthorities()) {
      currentRoles.add(role.getAuthority());
    }
    model.addAttribute("currentRoles", currentRoles);
    model.addAttribute("keyword", keyword);
    model.addAttribute("currentPage", pageNum);
    model.addAttribute("totalPages", employeePage.getTotalPages());
    model.addAttribute("totalItems", employeePage.getTotalElements());
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    model.addAttribute("numberElement", size);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    return "rawUI/employee/employees";
  }

  @Validated
  @PostMapping("/newEmployee")
  public String createEmployee(
      @Valid @ModelAttribute("employee") EmployeeCreateDto employeeCreateDto,
      BindingResult bindingResult,
      Authentication authentication,
      Model model) {
    String viewNewEmployee = "rawUI/employee/new_employee";
    if (bindingResult.hasErrors()) {
      model.addAttribute(ALL_ROLES_STRING, roleService.listRoles());
      return viewNewEmployee;
    }
    Employee employee = employeeService.createNewEmployee(employeeCreateDto, authentication);
    if (employee != null) return REDIRECT_EMPLOYEES;
    else return viewNewEmployee;
  }

  @GetMapping("/editEmployee/{id}")
  public String editEmployee(
      @Positive(message = "Id employee must higher than 0") @PathVariable("id") long id,
      Model model) {

    EmployeeUpdateDto employeeUpdateDto = employeeService.getEmployeePostDtoById(id);
    model.addAttribute("employeeUpdateDto", employeeUpdateDto);

    model.addAttribute("ownRoles", employeeUpdateDto.getRoles());
    List<Role> allRoles = roleService.listRoles();
    model.addAttribute(ALL_ROLES_STRING, allRoles);
    return "rawUI/employee/edit_employee";
  }

  @PostMapping("/editEmployee/{id}")
  public String updateEmployeeNoPassword(
      @PathVariable("id") Long id,
      @ModelAttribute EmployeeUpdateDto employeeUpdateDto,
      Authentication authentication) {
    Employee employee = employeeService.updateNoPassword(id, employeeUpdateDto, authentication);
    if (employee != null) {
      return REDIRECT_EMPLOYEES;
    }
    throw new RuntimeException("Failed update");
  }

  @GetMapping("/deleteEmployee/{id}")
  public String deleteEmployee(@PathVariable("id") long id, Authentication authentication) {
    boolean status = employeeService.deleteById(id, authentication);
    if (status) {
      return REDIRECT_EMPLOYEES;
    } else {
      throw new RuntimeException("Delete Unsuccessfully");
    }
  }

  @GetMapping("/newEmployee")
  public String showCreateEmployeePage(Model model) {
    model.addAttribute("employee", new EmployeeCreateDto());
    model.addAttribute(ALL_ROLES_STRING, roleService.listRoles());
    return "rawUI/employee/new_employee";
  }
}
