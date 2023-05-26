package com.bnd.ecommerce.repository;

import com.bnd.ecommerce.entity.employee.Employee;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("SELECT e FROM Employee e WHERE  e.email = :email")
  Employee getEmployeeByEmail(@Param("email") String email);

  Employee findByEmail(String email);

  @Query("SELECT e.email FROM Employee e WHERE e.id = :id")
  String findEmailById(@Param("id") long id);

  @Modifying
  @Query(value = "DELETE FROM employee_role WHERE employee_id =:employeeId", nativeQuery = true)
  void deleteEmployeeRoleByEmployeeId(@Param("employeeId") long employeeId);

  @Query(
      "SELECT DISTINCT e FROM Employee e JOIN e.roles r WHERE r.name LIKE CONCAT('%',:keyword,'%') OR CONCAT(e.email,' ',CAST(e.id as string ) ,' ', e.firstName, ' ', e.lastName, ' ', e.phone,' '  ) LIKE CONCAT('%',:keyword,'%') OR (:keyword like 'true' AND e.isEnabled = true )OR (:keyword LIKE 'false' AND e.isEnabled = false )")
  Page<Employee> search(@Param("keyword") String keyword, Pageable pageable);
}
