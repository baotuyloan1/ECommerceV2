package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.ProductModelAssembler;
import com.bnd.ecommerce.dto.api.CategoryRestAPI;
import com.bnd.ecommerce.dto.api.ProductRestAPI;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.entity.Laptop;
import com.bnd.ecommerce.entity.Phone;
import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.service.ProductService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

  private final ProductService productService;

  private final ProductModelAssembler productModelAssembler;

  public ProductRestController(
      ProductService productService, ProductModelAssembler productModelAssembler) {
    this.productService = productService;
    this.productModelAssembler = productModelAssembler;
  }

//  @PostMapping("/products")
//  public Product addOne(@Valid @RequestBody Product product) {
//    return productService.saveProduct(product);
//  }

  @GetMapping(value = {"", "/"})
  public @NotNull CollectionModel<EntityModel<ProductRestAPI>> getProducts() {
    List<EntityModel<ProductRestAPI>> productModelList = new ArrayList<>();
    for (Product product : productService.productList()) {
      ProductRestAPI productRestAPI = new ProductRestAPI();
      productRestAPI.setName(product.getName());
      productRestAPI.setBrand(product.getBrand().getName());
      productRestAPI.setDescription(product.getDescription());

      if (product instanceof Laptop laptop) {
        productRestAPI.setLaptop(laptop);
      }
      if (product instanceof Phone phone) {
        productRestAPI.setPhone(phone);
      }

      Set<CategoryRestAPI> categoryRestAPISet = new HashSet<>();
      for (Category category : product.getCategories()) {
        categoryRestAPISet.add(new CategoryRestAPI(category));
      }

      productRestAPI.setCategoryRestAPISet(categoryRestAPISet);

      EntityModel<ProductRestAPI> productEntityModel =
          productModelAssembler.toModel(productRestAPI);
      productModelList.add(productEntityModel);
    }
    return CollectionModel.of(productModelList);
  }
}
