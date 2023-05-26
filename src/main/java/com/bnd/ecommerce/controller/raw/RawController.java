package com.bnd.ecommerce.controller.raw;

import com.bnd.ecommerce.dto.ProductDto;
import com.bnd.ecommerce.entity.*;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.entity.stock.Warehouse;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.service.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawUI")
public class RawController {
  private final ProductService productService;
  private final LaptopService laptopService;
  private final BrandService brandService;
  private final CategoryService categoryService;

  private final RoleService roleService;
  private final EmployeeService employeeService;

  private final WareHouseService wareHouseService;

  private final StockService stockService;

  private final String REDIRECT_HOME = "redirect:/rawUI/";

  public RawController(
      ProductService productService,
      LaptopService laptopService,
      BrandService brandService,
      CategoryService categoryService,
      RoleService roleService,
      EmployeeService employeeService,
      WareHouseService wareHouseService,
      StockService stockService) {
    this.productService = productService;
    this.laptopService = laptopService;
    this.brandService = brandService;
    this.categoryService = categoryService;
    this.roleService = roleService;
    this.employeeService = employeeService;
    this.wareHouseService = wareHouseService;
    this.stockService = stockService;
  }

  @GetMapping("/")
  public String showAll(Model model, Authentication authentication) {
    return viewPage(1, "id", "desc", 3, model, authentication);
  }

  @GetMapping("/newPhone")
  public String showCreatePhone(Model model) {
    Phone phone = new Phone();
    List<Brand> brandList = brandService.brandList();
    model.addAttribute("phone", phone);
    model.addAttribute("brands", brandList);
    return "rawUI/new_phone";
  }

  @GetMapping("/page/{pageNum}")
  public String viewPage(
      @PathVariable("pageNum") @Positive(message = "Page number must be greater than 0")
          int pageNum,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir,
      @RequestParam(value = "size", defaultValue = "5")
          @Max(value = 100, message = "Page size must be lest than 100")
          @Min(value = 5, message = "Page size must ben greater than 4")
          int size,
      Model model,
      Authentication authentication) {
    if (pageNum < 0) pageNum = 1;
    Page<Category> pageCategories = categoryService.listAll(pageNum, sortField, sortDir, size);
    Page<ProductDto> productDtoPage = productService.productPage(pageNum, sortField, sortDir, size, null);
    Page<Laptop> pageLaptop = laptopService.laptopPage(pageNum, sortField, sortDir, size);
    Page<Brand> pageBrand = brandService.brandPage(pageNum, sortDir, sortField, size);
    Page<Warehouse> warehousePage =
        wareHouseService.pageWareHouse(pageNum, sortField, sortDir, size, null);
    Page<Stock> stockPage = stockService.stockPage(pageNum, sortField, sortDir, size, null);
    List<Category> listCategories = pageCategories.getContent();
    List<ProductDto> listProducts = productDtoPage.getContent();
    List<Laptop> listLaptops = pageLaptop.getContent();
    List<Role> roles = roleService.listRoles();
    List<Brand> listBrands = pageBrand.getContent();
    List<Warehouse> warehouseList = warehousePage.getContent();
    Page<Employee> employeePage = employeeService.listAll(size, pageNum, sortField, sortDir, null);

    model.addAttribute("stockList", stockPage.getContent());
    model.addAttribute("listEmployees", employeePage.getContent());
    model.addAttribute("listRoles", roles);
    model.addAttribute("listCategories", listCategories);
    model.addAttribute("listProducts", listProducts);
    model.addAttribute("listLaptops", listLaptops);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    model.addAttribute("listBrands", listBrands);
    model.addAttribute("currentPageCategories", pageNum);
    model.addAttribute("totalPagesCategories", pageCategories.getTotalPages());
    model.addAttribute("warehouseList", warehouseList);
    ArrayList<String> currentRoles = new ArrayList<>();
    if (authentication != null) {
      for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
        currentRoles.add(grantedAuthority.getAuthority());
      }
    }

    model.addAttribute("currentRoles", currentRoles);

    return "rawUI/home";
  }

  @GetMapping("/login")
  public String viewLoginPage() {
    return "/rawUI/login";
  }

  @GetMapping("/admin/newRole")
  public String showNewRolePage(Model model) {
    model.addAttribute("role", new Role());
    return "/rawUI/role/new_role";
  }

  @PostMapping("/admin/saveRole")
  public String saveRole(@Valid @ModelAttribute("role") Role role) {
    Role savedRole = roleService.save(role);
    return savedRole != null ? REDIRECT_HOME : "/rawUI/role/new_role";
  }

  @GetMapping("/admin/editRole/{id}")
  public String showEditRolePage(Model model, @PathVariable("id") int idRole) {
    Role role = roleService.findById(idRole);
    model.addAttribute("role", role);
    return "rawUI/role/edit_role";
  }

  @PostMapping("/admin/editRole/{id}")
  public String updateRole(@Valid @ModelAttribute("role") Role role, @PathVariable("id") int id) {
    Role updatedRole = roleService.findById(id);
    if (updatedRole != null) {
      role.setId(id);
      roleService.save(role);
      return REDIRECT_HOME;
    } else {
      throw new ResourceNotFoundException("The role does not exist in the system");
    }
  }

  @GetMapping("/admin/deleteRole/{id}")
  public String deleteRole(@PathVariable("id") int id) {
    boolean isDeleted = roleService.deleteById(id);
    if (isDeleted) {
      return REDIRECT_HOME;
    } else {
      throw new RuntimeException("Unsuccessful !");
    }
  }

  @GetMapping("/errorPage/403")
  public String errorPage403() {
    return "rawUI/403";
  }
}
