package com.bnd.ecommerce.controller.raw;

import com.bnd.ecommerce.dto.WarehouseDto;
import com.bnd.ecommerce.entity.stock.Warehouse;
import com.bnd.ecommerce.service.WareHouseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawUI/admin/warehouses")
public class WareHouseController {

  private final WareHouseService wareHouseService;
  private static final String REDIRECT_WAREHOUSES = "redirect:/rawUI/admin/warehouses/1";

  public WareHouseController(WareHouseService wareHouseService) {
    this.wareHouseService = wareHouseService;
  }

  @GetMapping("/{numPage}")
  public String showWareHouseArray(
      Model model,
      @RequestParam(value = "itemsNumber", defaultValue = "10") int numbersItem,
      @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      @PathVariable(value = "numPage") int numPage,
      @RequestParam(value = "keyword", defaultValue = "") String keyword) {
    Page<Warehouse> warehousePage =
        wareHouseService.pageWareHouse(numPage, sortField, sortDir, numbersItem, keyword);

    model.addAttribute("currentPage", numPage);
    model.addAttribute("warehousePage", warehousePage);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    model.addAttribute("numberElement", numbersItem);
    model.addAttribute("warehouseList", warehousePage.getContent());
    model.addAttribute("keyword", keyword);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("currentPage", numPage);
    model.addAttribute("totalPages", warehousePage.getTotalPages());
    return "rawUI/warehouse/warehouses";
  }

  @GetMapping("/newWarehouse")
  public String showNewWarehouse(Model model) {
    model.addAttribute(new WarehouseDto());
    return "rawUI/warehouse/new_warehouse";
  }

  @PostMapping("/saveWarehouse")
  public String saveWarehouse(@ModelAttribute("warehouseDto") WarehouseDto warehouseDto) {
    Warehouse savedWarehouse = wareHouseService.save(warehouseDto);
    if (savedWarehouse != null) {
      return REDIRECT_WAREHOUSES;
    } else {
      return "rawUI/warehouse/new_warehouse";
    }
  }

  @GetMapping("/editWarehouse/{id}")
  public String showEditWarehouse(@PathVariable("id") int id, Model model) {
    WarehouseDto warehouseDto = wareHouseService.findWarehouseDtoById(id);
    model.addAttribute("warehouseDto", warehouseDto);
    return "rawUI/warehouse/edit_warehouse";
  }

  @PostMapping("/updateWarehouse")
  public String updateWarehouse(@ModelAttribute("warehouseDto") WarehouseDto warehouseDto) {
    Warehouse savedWarehouse = wareHouseService.save(warehouseDto);
    if (savedWarehouse != null) return REDIRECT_WAREHOUSES;
    else return "rawUI/warehouse/edit_warehouse";
  }

  @GetMapping("/deleteWarehouse/{id}")
  public String deleteWarehouse(@PathVariable("id") int id) {
    boolean isDeleted = wareHouseService.deleteById(id);
    return isDeleted ? REDIRECT_WAREHOUSES : null;
  }
}
