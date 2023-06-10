package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.assembler.ProductModelAssembler;
import com.bnd.ecommerce.assembler.ProductRestAPIModelAssembler;
import com.bnd.ecommerce.dto.CategoryDto;
import com.bnd.ecommerce.dto.ProductDto;
import com.bnd.ecommerce.dto.api.ProductFilterDTO;
import com.bnd.ecommerce.service.ProductService;
import java.util.*;
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

  @GetMapping(value = "/filters")
  public List<ProductDto> filter(
      @RequestParam(value = "categories", required = false) List<Integer> categories,
      @RequestParam(value = "brands", required = false) List<Integer> brands,
      @RequestParam(value = "sortPrice", required = false) String sortDir) {
    Set<ProductDto> result = null;
    List<ProductDto> productDtoList = productService.productDtoList();
    if (categories != null && !categories.isEmpty() || brands != null && !brands.isEmpty()) {
      result = new HashSet<>();
      if (categories != null && !categories.isEmpty()) {
        for (ProductDto productDto : productDtoList) {
          for (CategoryDto categoryDto : productDto.getCategoryDtoSet()) {
            if (categories.contains(categoryDto.getId())) {
              result.add(productDto);
            }
          }
        }
      }
      if (brands != null && !brands.isEmpty()) {
        for (ProductDto productDto : productDtoList) {
          if (brands.contains(productDto.getBrandDto().getId())) {
            result.add(productDto);
          }
        }
      }
    } else {
      result = new HashSet<ProductDto>(productDtoList);
    }
    List<ProductDto> resultList = new ArrayList(result);
    List<EntityModel<ProductDto>> productModelList = new ArrayList<>();
    if (sortDir != null && !sortDir.isEmpty()) {
      if (sortDir.equals("asc")) {
        quickSort(resultList, 0, resultList.size() - 1);
      } else {
        quickSortReverse(resultList, 0, resultList.size() - 1);
      }
    }
//    for (ProductDto productDto : resultList) {
//      productModelList.add(productModelAssembler.toModel(productDto));
//    }

    return resultList;
//    return CollectionModel.of(productModelList);
  }

  private void quickSort(List<ProductDto> productDtoList, int low, int high) {
    if (low < high) {
      int pivotIndex = partition(productDtoList, low, high);
      quickSort(productDtoList, low, pivotIndex - 1);
      quickSort(productDtoList, pivotIndex + 1, high);
    }
  }

  private int partition(List<ProductDto> productDtoList, int low, int high) {
    ProductDto pivot = productDtoList.get(high);
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (productDtoList.get(j).getPrice() < pivot.getPrice()) {
        i++;
        swap(productDtoList, i, j);
      }
    }
    swap(productDtoList, i + 1, high);
    return i + 1;
  }

  private void swap(List<ProductDto> productDtoList, int i, int j) {
    ProductDto temp = productDtoList.get(i);
    productDtoList.set(i, productDtoList.get(j));
    productDtoList.set(j, temp);
  }

  public void quickSortReverse(List<ProductDto> productDtoList, int low, int high) {
    if (low < high) {
      int pivotIndex = partitionReverse(productDtoList, low, high);
      quickSortReverse(productDtoList, low, pivotIndex - 1);
      quickSortReverse(productDtoList, pivotIndex + 1, high);
    }
  }

  private int partitionReverse(List<ProductDto> productDtoList, int low, int high) {
    ProductDto pivot = productDtoList.get(high);
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (productDtoList.get(j).getPrice() > pivot.getPrice()) {
        i++;
        swap(productDtoList, i, j);
      }
    }
    swap(productDtoList, i + 1, high);
    return i + 1;
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
