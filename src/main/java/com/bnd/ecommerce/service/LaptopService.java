package com.bnd.ecommerce.service;

import com.bnd.ecommerce.dto.LaptopDto;
import com.bnd.ecommerce.entity.Laptop;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface LaptopService {
//
//  Laptop create(LaptopDto laptopDto, MultipartFile mainImage, MultipartFile[] imagesDetail);
//
//  List<Laptop> listLaptops();
//
  Page<Laptop> laptopPage(int numPage, String sortField, String sortDir, int size);
//
//  Laptop update(LaptopDto laptopDto, MultipartFile multipartFile);
}
