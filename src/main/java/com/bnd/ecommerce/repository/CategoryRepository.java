package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query(
      value = "SELECT c.parentCategory FROM Category c  WHERE c.id = :id")
  Category getParentCategoryByCategoryId(@Param("id") int id);

  @Query(value = "SELECT c FROM Category c WHERE c.parentCategory = null")
  List<Category> rootCategoryList();
}
