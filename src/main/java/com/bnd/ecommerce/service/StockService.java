package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.StockDto;
import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.entity.stock.StockID;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface StockService {

  Page<Stock> stockPage(int pageNum, String sortField, String sortDir, int size, String keyword);

  StockDto findStockDtoById(long productId, int warehouseId);

  boolean deleteById(long productId, int warehouseId);

  Stock create(StockDto stockDto, Authentication authentication);

  void update(StockDto stockDto, Authentication authentication, StockDto oldStockDto);

  boolean isExisted(long productId, int warehouseId);

  boolean isExisted(StockID stockID);

  long addQuantity(long newProductId, int newWarehouseId, long quantity);

  boolean isExisted(StockDto newStockDto, StockDto oldStockDto);
}
