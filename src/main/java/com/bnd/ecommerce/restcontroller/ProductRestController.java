package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.ProductModelAssembler;
import com.bnd.ecommerce.assembler.ProductRestAPIModelAssembler;
import com.bnd.ecommerce.dto.ProductDto;
import com.bnd.ecommerce.dto.api.ProductFilterDTO;
import com.bnd.ecommerce.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

  private final ProductService productService;

  private final ProductRestAPIModelAssembler productRestAPIModelAssembler;
  private final ProductModelAssembler productModelAssembler;

  public ProductRestController(
      ProductService productService,
      ProductRestAPIModelAssembler productRestAPIModelAssembler,
      ProductModelAssembler productModelAssembler) {
    this.productService = productService;
    this.productRestAPIModelAssembler = productRestAPIModelAssembler;
    this.productModelAssembler = productModelAssembler;
  }

  //  @PostMapping("/products")
  //  public Product addOne(@Valid @RequestBody Product product) {
  //    return productService.saveProduct(product);
  //  }

  @GetMapping(value = {"", "/"})
  public @NotNull CollectionModel<EntityModel<ProductDto>> getProducts() {
    List<EntityModel<ProductDto>> productModelList = new ArrayList<>();
    List<ProductDto> productDtoList = productService.productDtoList();
    for (ProductDto productDto : productDtoList) {
      productModelList.add(productModelAssembler.toModel(productDto));
    }
    return CollectionModel.of(productModelList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<ProductDto>> getOne(@PathVariable("id") long id) {
    ProductDto productDto = productService.findById(id);
    return ResponseEntity.ok(productModelAssembler.toModel(productDto));
  }

  @PostMapping("/search")
  public ResponseEntity<CollectionModel<EntityModel<ProductDto>>> search(
    @Valid @RequestBody ProductFilterDTO productFilterDTO) {
    List<EntityModel<ProductDto>> productModelList = new ArrayList<>();
    Page<ProductDto> productDtoPage =
        productService.productPage(
            productFilterDTO.getPageNum(),
            productFilterDTO.getSortField(),
            productFilterDTO.getSortDir(),
            productFilterDTO.getSize(),
            productFilterDTO.getKeyword());
    for (ProductDto productDto : productDtoPage.getContent()) {
      productModelList.add(productModelAssembler.toModel(productDto));
    }
    return ResponseEntity.ok(CollectionModel.of(productModelList));
  }
}
