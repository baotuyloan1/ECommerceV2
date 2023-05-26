package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.LaptopDto;
import com.bnd.ecommerce.entity.Category;
import com.bnd.ecommerce.entity.ImageDetail;
import com.bnd.ecommerce.entity.Laptop;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.LaptopRepository;
import com.bnd.ecommerce.service.ImageDetailService;
import com.bnd.ecommerce.service.LaptopService;
import com.bnd.ecommerce.service.ProductService;
import com.bnd.ecommerce.util.FileUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LaptopServiceImpl implements LaptopService {
  private final LaptopRepository laptopRepository;
  private final ProductService productService;
  private final MapStructMapper mapStructMapper;
  private final ImageDetailService imageDetailService;

  private final String ROOT_DIR = "laptop-photos/";

  public LaptopServiceImpl(
      LaptopRepository laptopRepository,
      ProductService productService,
      MapStructMapper mapStructMapper,
      ImageDetailService imageDetailService) {
    this.laptopRepository = laptopRepository;
    this.productService = productService;
    this.mapStructMapper = mapStructMapper;
    this.imageDetailService = imageDetailService;
  }

//  public Laptop create(LaptopDto laptopDto, MultipartFile mainImage, MultipartFile[] imagesDetail) {
//    String fileName =
//        StringUtils.cleanPath(Objects.requireNonNull(mainImage.getOriginalFilename()));
//    Laptop laptop = mapStructMapper.laptopDtoToLaptop(laptopDto);
//    laptop.getProduct().setImage(fileName);
//    Category category =
//        mapStructMapper.categoryDtoToCategory(laptopDto.getProductDto().getCategoryDto());
//    Set<Category> categories = new HashSet<>();
//    productService.findRootCategory(category, categories);
//    laptop.getProduct().setCategories(categories);
//    Laptop savedLaptop = laptopRepository.save(laptop);
//    String uploadDir = ROOT_DIR + savedLaptop.getProduct().getId();
//    try {
//      FileUtil.saveFile(uploadDir, fileName, mainImage);
//      for (MultipartFile imageDetail : imagesDetail) {
//        if (!imageDetail.isEmpty()) {
//          String fileNameDetail =
//              StringUtils.cleanPath(Objects.requireNonNull(imageDetail.getOriginalFilename()));
//          long size = FileUtil.saveFile(uploadDir, fileNameDetail, imageDetail);
//          ImageDetail productDetailImage = new ImageDetail();
//          productDetailImage.setDescription("Description of " + fileNameDetail);
//          productDetailImage.setProduct(savedLaptop.getProduct());
//          productDetailImage.setName(fileNameDetail);
//          productDetailImage.setSize(size);
//          imageDetailService.save(productDetailImage);
//        }
//      }
//    } catch (IOException e) {
//      throw new RuntimeException("Save image failed");
//    }
//    return savedLaptop;
//  }
//
//  @Override
//  public List<Laptop> listLaptops() {
//    return laptopRepository.findAll();
//  }
//
  @Override
  public Page<Laptop> laptopPage(int numPage, String sortField, String sortDir, int size) {
    PageRequest pageRequest =
        PageRequest.of(
            numPage - 1,
            size,
            sortDir.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
    return laptopRepository.findAll(pageRequest);
  }
//
//  @Transactional
//  @Override
//  public Laptop update(LaptopDto laptopDto, MultipartFile multipartFile) {
//    Laptop laptop = mapStructMapper.laptopDtoToLaptop(laptopDto);
//    try {
//      if (!multipartFile.isEmpty()) {
//        String fileName =
//            StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        String uploadDir = ROOT_DIR + laptopDto.getProductDto().getId();
//        FileUtil.deleteFile(uploadDir, laptopDto.getProductDto().getImage());
//        uploadDir = ROOT_DIR + laptop.getProduct().getId();
//        laptop.getProduct().setImage(fileName);
//        FileUtil.saveFile(uploadDir, fileName, multipartFile);
//      }
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//    Category category =
//        mapStructMapper.categoryDtoToCategory(laptopDto.getProductDto().getCategoryDto());
//    Set<Category> categories = new HashSet<>();
//    productService.findRootCategory(category, categories);
//    laptop.getProduct().setCategories(categories);
//    return laptopRepository.save(laptop);
//  }
}
