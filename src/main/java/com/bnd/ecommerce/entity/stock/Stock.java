package com.bnd.ecommerce.entity.stock;

import com.bnd.ecommerce.entity.CreateUpdateTimeStamp;
import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.entity.employee.Employee;
import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock extends CreateUpdateTimeStamp {

    @EmbeddedId
    private StockID id;

    public StockID getId() {
        return id;
    }

    public void setId(StockID id) {
        this.id = id;
    }

    private long quantityInStock;



  @ManyToOne
  @JoinColumn(name = "created_employee_id")
  private Employee createdEmployee;


  @ManyToOne
  @JoinColumn(name = "updated_employee_id")
 private Employee updatedEmployee;


  public Employee getCreatedEmployee() {
    return createdEmployee;
  }

  public void setCreatedEmployee(Employee createdEmployee) {
    this.createdEmployee = createdEmployee;
  }

  public Employee getUpdatedEmployee() {
    return updatedEmployee;
  }

  public void setUpdatedEmployee(Employee updatedEmployee) {
    this.updatedEmployee = updatedEmployee;
  }


    public long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
