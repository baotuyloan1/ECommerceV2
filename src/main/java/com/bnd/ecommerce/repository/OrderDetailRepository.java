package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.order.OrderDetail;
import com.bnd.ecommerce.entity.order.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderProductPK> {}
