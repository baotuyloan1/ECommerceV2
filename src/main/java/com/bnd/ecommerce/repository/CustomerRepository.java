package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findByEmail(String email);

  Optional<Customer> findByUserName(String userName);
}
