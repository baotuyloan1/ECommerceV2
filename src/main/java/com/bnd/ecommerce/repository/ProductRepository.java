package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(
      value =
          "SELECT DISTINCT p FROM Product p INNER JOIN p.categories c WHERE CONCAT(p.name,' ',CAST(p.id AS string ),' ',p.brand.name,' ',p.brand.description,' ',c.name,' ' ,c.description  )LIKE CONCAT('%',:keyword,'%') ")
  Page<Product> search(@Param("keyword") String keyword, Pageable pageable);

  @Modifying
  @Query(value = "DELETE FROM product_category WHERE product_id=:productId", nativeQuery = true)
  void deleteProductCategoryByProductId(@Param("productId") long id);


  Product findByName(String name);
}
