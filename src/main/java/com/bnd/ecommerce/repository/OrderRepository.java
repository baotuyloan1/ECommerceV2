package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.entity.order.Order;
import com.bnd.ecommerce.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long > {

    @Query("SELECT o FROM Order o INNER JOIN o.customerAddress cd INNER JOIN cd.customer c WHERE c.id= :id")
    List<Order> getOrdersByCusId(@Param("id") long id);


//    @Modifying
//    @Query("UPDATE Order o  SET o.status = :status WHERE o.customerAddress.customer.id = :customerId AND o.id = :orderId")
//    void updateStatus(@Param("status")OrderStatus status, @Param("customerId")Long customerId, @Param("orderId")long orderId);

    @Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.customerAddress.customer.id = :customerId")
    Optional<Order> getOrderByIdAndCustomerId(@Param("orderId") long orderId, @Param("customerId")long customerId);
}
