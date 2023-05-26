package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, Long> {
}
