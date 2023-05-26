package com.bnd.ecommerce.controller.raw;

import com.bnd.ecommerce.dto.QuantityDto;
import com.bnd.ecommerce.dto.StockDto;
import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.service.ProductService;
import com.bnd.ecommerce.service.StockService;
import com.bnd.ecommerce.service.WareHouseService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawUI/manager/stocks")
public class StockController {

  private static final String REDIRECT_STOCKS = "redirect:/rawUI/manager/stocks/1";
  private final StockService stockService;
  private final ProductService productService;
  private final WareHouseService wareHouseService;

  public StockController(
      StockService stockService, ProductService productService, WareHouseService wareHouseService) {
    this.stockService = stockService;
    this.productService = productService;
    this.wareHouseService = wareHouseService;
  }

  @GetMapping("/{numPage}")
  public String showStocks(
      Model model,
      @RequestParam(value = "itemsNumber", defaultValue = "10") int numbersItem,
      @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      @PathVariable(value = "numPage") int numPage,
      @RequestParam(value = "keyword", defaultValue = "") String keyword) {
    Page<Stock> stockPage =
        stockService.stockPage(numPage, sortField, sortDir, numbersItem, keyword);
    model.addAttribute("currentPage", numPage);
    model.addAttribute("stockPage", stockPage);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    model.addAttribute("numberElement", numbersItem);
    model.addAttribute("stockList", stockPage.getContent());
    model.addAttribute("keyword", keyword);
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("currentPage", numPage);
    model.addAttribute("totalPages", stockPage.getTotalPages());
    return "rawUI/stock/stocks";
  }

  @GetMapping("/newStock")
  public String showNewStock(Model model) {
    model.addAttribute("stockDto", new StockDto());
    loadData(model);
    return "rawUI/stock/new_stock";
  }

  private void loadData(Model model) {
    model.addAttribute("productList", productService.productList());
    model.addAttribute("warehouseList", wareHouseService.warehouseList());
  }

  @PostMapping("/saveStock")
  public String createNewStock(
      @Valid @ModelAttribute("stockDto") StockDto stockDto,
      BindingResult bindingResult,
      Authentication authentication,
      Model model) {
    if (bindingResult.hasErrors()) {
      loadData(model);
      return "rawUI/stock/new_stock";
    }
    stockService.create(stockDto, authentication);
    return REDIRECT_STOCKS;
  }

  @GetMapping("/addQuantity/{productId}/{warehouseId}")
  public String showAddQuantity(
      @PathVariable("productId") long productId,
      @PathVariable("warehouseId") int warehouseId,
      Model model) {

    model.addAttribute("productId", productId);
    model.addAttribute("warehouseId", warehouseId);
    model.addAttribute("quantityDto", new QuantityDto());
    return "rawUI/stock/add_quantity";
  }

  @PostMapping("/addQuantity")
  public String addQuantity(
      @ModelAttribute("quantityDto") QuantityDto quantityDto,
      @RequestParam("productId") long productId,
      @RequestParam("warehouseId") int warehouseId) {

    stockService.addQuantity(productId, warehouseId, quantityDto.getQuantity());
    return REDIRECT_STOCKS;
  }

  @GetMapping("/editStock/{productId}/{warehouseId}")
  public String showEditStock(
      @PathVariable("productId") long productId,
      @PathVariable("warehouseId") int warehouseId,
      Model model) {
    StockDto stockDto = stockService.findStockDtoById(productId, warehouseId);
    model.addAttribute("stockDto", stockDto);
    model.addAttribute("oldProductId", stockDto.getProductId());
    model.addAttribute("oldWarehouseId", stockDto.getWarehouseId());
    loadData(model);
    return "rawUI/stock/edit_stock";
  }

  @PostMapping("/updateStock")
  public String updateStock(
      @ModelAttribute("stockDto") StockDto stockDto,
      @RequestParam("oldProductId") long oldProductId,
      @RequestParam("oldWarehouseId") int oldWarehouseId,
      Authentication authentication,
      Model model) {
    StockDto oldStockDto = new StockDto();
    oldStockDto.setProductId(oldProductId);
    oldStockDto.setWarehouseId(oldWarehouseId);
    if (stockService.isExisted(stockDto, oldStockDto)) {
      model.addAttribute("oldProductId", oldProductId);
      model.addAttribute("oldWarehouseId", oldWarehouseId);
      loadData(model);
      model.addAttribute("existedMessage", "These products were existed in this warehouse");
      return "rawUI/stock/edit_stock";
    }
    stockService.update(stockDto, authentication, oldStockDto);
    return REDIRECT_STOCKS;
  }

  @GetMapping("/deleteStock/{productId}/{warehouseId}")
  public String deleteStock(
      @PathVariable("productId") long productId, @PathVariable("warehouseId") int warehouseId) {
    boolean isDeleted = stockService.deleteById(productId, warehouseId);
    return isDeleted ? REDIRECT_STOCKS : null;
  }
}
