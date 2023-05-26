package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long > {

}
