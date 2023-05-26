package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.StockDto;
import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.entity.stock.StockID;
import com.bnd.ecommerce.entity.stock.Warehouse;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.StockRepository;
import com.bnd.ecommerce.service.EmployeeService;
import com.bnd.ecommerce.service.StockService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

  private final StockRepository stockRepository;

  private final MapStructMapper mapStructMapper;

  private final EmployeeService employeeService;

  public StockServiceImpl(
      StockRepository stockRepository,
      MapStructMapper mapStructMapper,
      EmployeeService employeeService) {
    this.stockRepository = stockRepository;
    this.mapStructMapper = mapStructMapper;
    this.employeeService = employeeService;
  }

  @Override
  public Page<Stock> stockPage(
      int pageNum, String sortField, String sortDir, int size, String keyword) {
    Pageable pageable =
        PageRequest.of(
            pageNum - 1,
            size,
            sortDir.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
    if (keyword != null && !keyword.trim().equals("")) {
      return stockRepository.searchAllBy(keyword, pageable);
    }
    return stockRepository.findAll(pageable);
  }

  @Override
  public StockDto findStockDtoById(long productId, int warehouseId) {
    Optional<Stock> stock = stockRepository.findById(getStockID(productId, warehouseId));
    if (stock.isPresent()) {
      StockDto stockDto = mapStructMapper.stockToStockDto(stock.get());
      stockDto.setProductId(productId);
      stockDto.setWarehouseId(warehouseId);
      return stockDto;
    } else throw new ResourceNotFoundException("Stock not found");
  }

  StockID getStockID(long productId, int warehouseId) {
    Product product = new Product();
    product.setId(productId);
    Warehouse warehouse = new Warehouse();
    warehouse.setId(warehouseId);
    return new StockID(product, warehouse);
  }

  @Override
  public boolean deleteById(long productId, int warehouseId) {
    StockID stockID = getStockID(productId, warehouseId);
    stockRepository.deleteById(stockID);
    return !stockRepository.existsById(stockID);
  }

  @Override
  @Transactional
  public Stock create(StockDto stockDto, Authentication authentication) {
    StockID id = getStockID(stockDto.getProductId(), stockDto.getWarehouseId());
    Stock stock = mapStructMapper.stockDtoToStock(stockDto);
    stock.setId(id);
    Employee employee = employeeService.findByEmail(authentication.getName());
    stock.setUpdatedEmployee(employee);
    stock.setCreatedEmployee(employee);
    if (isExisted(id)) {
      stockRepository.addQuantity(
          id, stockDto.getQuantityInStock(), new Timestamp(new Date().getTime()), employee.getId());
      return stockRepository.findById(id).orElseThrow();
    } else {
      return stockRepository.save(stock);
    }
  }

  @Override
  @Transactional
  public void update(StockDto stockDto, Authentication authentication, StockDto oldStockDto) {
    Employee employee = employeeService.findByEmail(authentication.getName());
    if (stockDto.getProductId() == oldStockDto.getProductId()
        && stockDto.getWarehouseId() == oldStockDto.getWarehouseId()) {
      stockRepository.updateQuantity(
          stockDto.getQuantityInStock(),
          getStockID(stockDto.getProductId(), stockDto.getWarehouseId()),
          new Timestamp(new Date().getTime()),
          employee.getId());
    } else {
      stockRepository.update(
          stockDto.getProductId(),
          stockDto.getWarehouseId(),
          stockDto.getQuantityInStock(),
          oldStockDto.getProductId(),
          oldStockDto.getWarehouseId(),
          new Timestamp(new Date().getTime()),
          employee.getId());
    }
  }

  @Override
  public boolean isExisted(long productId, int warehouseId) {
    return stockRepository.existsById(getStockID(productId, warehouseId));
  }

  @Override
  public boolean isExisted(StockID stockID) {
    return stockRepository.existsById(stockID);
  }

  @Override
  @Transactional
  public long addQuantity(long productId, int warehouseId, long quantity) {
    stockRepository.addQuantity(productId, warehouseId, quantity);
    return stockRepository.getQuantityById(productId, warehouseId);
  }

  @Override
  public boolean isExisted(StockDto newStockDto, StockDto oldStockDto) {
    long count =
        stockRepository.countByNewStockId(
            getStockID(newStockDto.getProductId(), newStockDto.getWarehouseId()),
            getStockID(oldStockDto.getProductId(), oldStockDto.getWarehouseId()));
    return count > 0;
  }
}
