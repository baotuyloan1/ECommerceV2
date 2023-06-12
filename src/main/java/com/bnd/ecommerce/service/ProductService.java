package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.ProductDto;
import com.bnd.ecommerce.entity.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

  List<Product> productList();
  //
  //  Product saveProduct(Product product);

  @Transactional
  Product create(ProductDto productDto, MultipartFile mainImage, MultipartFile[] imagesDetail);
  //
  Page<ProductDto> productPage(
      int numPage, String sortField, String sortDir, int size, String keyword);
  //
  ProductDto findById(long id);
  //
  boolean deleteProductById(long id);
  //
  //  Category findRootCategory(Category category, Set<Category> categories);
  //
  ProductDto findProductDtoByName(String name);

  Product findByName(String name);

  Product update(ProductDto productDto, MultipartFile multipartFile);

  List<ProductDto> productDtoList();

  Page<ProductDto> filterPage(List<Integer> categories, List<Integer> brands, String sortDir, int page, int maxPrice);
}
