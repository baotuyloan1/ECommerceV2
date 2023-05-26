package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.entity.stock.StockID;
import java.sql.Timestamp;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockID> {

  @Modifying
  @Query(
      value =
          "UPDATE Stock s SET s.quantityInStock = s.quantityInStock + :quantity WHERE s.id.product.id = :productId AND  s.id.warehouse.id = :warehouseId")
  void addQuantity(
      @Param("productId") long productId,
      @Param("warehouseId") int warehouseId,
      @Param("quantity") long quantity);

  @Modifying
  @Query(
      value =
          "UPDATE Stock s SET s.quantityInStock = s.quantityInStock  + :quantity  , s.updateTime = :updateTime, s.updatedEmployee.id = :employeeId WHERE s.id = :stockID")
  void addQuantity(
      @Param("stockID") StockID stockID,
      @Param("quantity") long quantity,
      @Param("updateTime") Timestamp updateTime,
      @Param("employeeId") long employeeId);

  @Query(
      value =
          "SELECT s.quantityInStock FROM Stock s WHERE s.id.product.id = :productId AND  s.id.warehouse.id = :warehouseId")
  long getQuantityById(@Param("productId") long productId, @Param("warehouseId") int warehouseId);

  @Transactional
  @Modifying
  @Query(
      value =
          "UPDATE Stock s SET s.id.product.id = :productId, s.id.warehouse.id = :warehouseId,"
              + "s.quantityInStock = :quantityInStock, s.updateTime = :updateTime, s.updatedEmployee.id = :employeeId WHERE s.id.product.id = :oldProductId AND s.id.warehouse.id = :oldWarehouseId")
  void update(
      @Param("productId") long productId,
      @Param("warehouseId") int warehouseId,
      @Param("quantityInStock") long quantityInStock,
      @Param("oldProductId") long oldProductId,
      @Param("oldWarehouseId") int oldWarehouseId,
      @Param("updateTime") Timestamp updateTime,
      @Param("employeeId") long employeeId);

  @Modifying
  @Query(
      value =
          "UPDATE Stock s SET s.quantityInStock = :quantity, s.updateTime = :updateTime, s.updatedEmployee.id = :employeeId WHERE s.id = :stockId")
  void updateQuantity(
      @Param("quantity") long quantity,
      @Param("stockId") StockID stockID,
      @Param("updateTime") Timestamp update,
      @Param("employeeId") long employeeId);

  @Query("SELECT count(*) FROM Stock s WHERE s.id = :stockID AND s.id != :oldStockId")
  long countByNewStockId(
      @Param("stockID") StockID newStockID, @Param("oldStockId") StockID oldStockID);

  @Query(
      "SELECT DISTINCT s FROM Stock s JOIN s.id.product p JOIN p.categories c JOIN p.brand b WHERE CONCAT(s.id.product.name,' ',s.id.product.description, ' ' , s.id.warehouse.warehouseName, ' ' ,s.createdEmployee.firstName,' ', s.createdEmployee.lastName,' ',s.updatedEmployee.firstName, ' ', s.updatedEmployee.lastName,' ',CAST(s.quantityInStock AS string),' ', c.name , ' ',b.name ) LIKE CONCAT('%',:keyword,'%') ")
  Page<Stock> searchAllBy(@Param("keyword") String keyword, Pageable pageable);
}
