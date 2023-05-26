package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {}
