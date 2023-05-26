package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.ImageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDetailRepository extends JpaRepository<ImageDetail, Long> {}
