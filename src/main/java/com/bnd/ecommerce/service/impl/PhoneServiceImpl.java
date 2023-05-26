package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.PhoneDto;
import com.bnd.ecommerce.dto.ProductDto;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.entity.ImageDetail;
import com.bnd.ecommerce.entity.Phone;
import com.bnd.ecommerce.entity.Product;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.PhoneRepository;
import com.bnd.ecommerce.repository.ProductRepository;
import com.bnd.ecommerce.service.*;
import com.bnd.ecommerce.util.FileUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhoneServiceImpl implements PhoneService {
  private final PhoneRepository phoneRepository;

  private final MapStructMapper mapStructMapper;

  private final ProductService productService;
  private final CategoryService categoryService;

  private final ImageDetailService imageDetailService;

  private static final String ROOT_DIR = "phone-photos/";
  private final ProductRepository productRepository;

  public PhoneServiceImpl(
      PhoneRepository phoneRepository,
      MapStructMapper mapStructMapper,
      ProductService productService,
      CategoryService categoryService,
      ImageDetailService imageDetailService,
      ProductRepository productRepository) {
    this.phoneRepository = phoneRepository;
    this.mapStructMapper = mapStructMapper;
    this.productService = productService;
    this.categoryService = categoryService;
    this.imageDetailService = imageDetailService;
    this.productRepository = productRepository;
  }




//  @Override
//  public Page<Phone> phonePage(
//      int numPage, String sortField, String sortDir, int size, String keyword) {
//    Pageable pageable =
//        PageRequest.of(
//            numPage,
//            size,
//            sortDir.equals("asc")
//                ? Sort.by(sortField).ascending()
//                : Sort.by(sortField).descending());
//    if (keyword != null && !keyword.trim().equals("")) {
//      return phoneRepository.searchPhone(keyword, pageable);
//    }
//
//    return phoneRepository.findAll(pageable);
//  }
//
//  @Transactional
//  @Override
//  public Phone update(PhoneDto phoneDto, MultipartFile multipartFile) {
//    Phone phone = mapStructMapper.phoneDtoToPhone(phoneDto);
//    try {
//      if (!multipartFile.isEmpty()) {
//        String fileName =
//            StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        String uploadDir = ROOT_DIR + phoneDto.getProductDto().getId();
//        FileUtil.deleteFile(uploadDir, phoneDto.getProductDto().getImage());
//        uploadDir = ROOT_DIR + phone.getProduct().getId();
//        phone.getProduct().setImage(fileName);
//        FileUtil.saveFile(uploadDir, fileName, multipartFile);
//      }
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//    Category category =
//        mapStructMapper.categoryDtoToCategory(phoneDto.getProductDto().getCategoryDto());
//    Set<Category> categories = new HashSet<>();
//    productService.findRootCategory(category, categories);
//    phone.getProduct().setCategories(categories);
//    return phoneRepository.save(phone);
//  }
//
//  @Transactional
//  @Override
//  public Phone save(PhoneDto phoneDto) {
//    Phone phone = mapStructMapper.phoneDtoToPhone(phoneDto);
//    Category category =
//        mapStructMapper.categoryDtoToCategory(phoneDto.getProductDto().getCategoryDto());
//    Set<Category> categories = new HashSet<>();
//    productService.findRootCategory(category, categories);
//    phone.getProduct().setCategories(categories);
//    return phoneRepository.save(phone);
//  }
//
//  @Override
//  public List<Phone> listPhones() {
//    return phoneRepository.findAll();
//  }
}
