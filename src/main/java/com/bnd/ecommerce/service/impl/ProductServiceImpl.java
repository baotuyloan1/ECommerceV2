package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.comparator.CategoryComparator;
import com.bnd.ecommerce.dto.*;
import com.bnd.ecommerce.entity.*;
import com.bnd.ecommerce.exception.CreateFailException;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.LaptopRepository;
import com.bnd.ecommerce.repository.PhoneRepository;
import com.bnd.ecommerce.repository.ProductRepository;
import com.bnd.ecommerce.repository.TabletRepository;
import com.bnd.ecommerce.service.CategoryService;
import com.bnd.ecommerce.service.ImageDetailService;
import com.bnd.ecommerce.service.ProductService;
import com.bnd.ecommerce.util.FileUtil;
import java.io.IOException;
import java.util.*;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;
  private PhoneRepository phoneRepository;

  private final MapStructMapper mapStructMapper;

  private final CategoryService categoryService;
  private final ImageDetailService imageDetailService;

  private static final String PHONE_DIR = "images/phone-photos/";
  private static final String LAPTOP_DIR = "images/laptop-photos/";
  private static final String TABLET_DIR = "images/laptop-photos/";
  private final LaptopRepository laptopRepository;
  private final TabletRepository tabletRepository;

  public ProductServiceImpl(
      ProductRepository productRepository,
      MapStructMapper mapStructMapper,
      PhoneRepository phoneRepository,
      CategoryService categoryService1,
      ImageDetailService imageDetailService,
      LaptopRepository laptopRepository,
      TabletRepository tabletRepository) {
    this.productRepository = productRepository;
    this.mapStructMapper = mapStructMapper;
    this.categoryService = categoryService1;
    this.imageDetailService = imageDetailService;
    this.phoneRepository = phoneRepository;
    this.laptopRepository = laptopRepository;
    this.tabletRepository = tabletRepository;
  }

  @Override
  public List<Product> productList() {
    return productRepository.findAll();
  }

  //  @Override
  //  public Product saveProduct(Product product) {
  //    return productRepository.save(product);
  //  }

  @Override
  @Transactional
  public Product create(
      ProductDto productDto, MultipartFile mainImage, MultipartFile[] imagesDetail) {
    String fileName =
        StringUtils.cleanPath(Objects.requireNonNull(mainImage.getOriginalFilename()));
    productDto.setImage(fileName);
    Category category = mapStructMapper.categoryDtoToCategory(productDto.getMainCategoryDto());
    Set<Category> categories = new HashSet<>();
    findRootCategory(category, categories);
    String uploadDir = "";
    Product savedProduct = null;

    if (productDto instanceof PhoneDto phoneDto) {
      Phone savedPhone = mapStructMapper.phoneDtoToPhone(phoneDto);
      savedPhone.setCategorySet(categories);
      savedProduct = phoneRepository.save(savedPhone);
      uploadDir = PHONE_DIR + savedPhone.getId();
    }

    if (productDto instanceof LaptopDto laptopDto) {
      Laptop savedLaptop = mapStructMapper.laptopDtoToLaptop(laptopDto);
      savedLaptop.setCategorySet(categories);
      savedProduct = laptopRepository.save(savedLaptop);
      uploadDir = LAPTOP_DIR + savedLaptop.getId();
    }

    if (productDto instanceof TabletDto tabletDto) {
      Tablet savedTabled = mapStructMapper.tabletDtoToTablet(tabletDto);
      savedTabled.setCategorySet(categories);
      savedProduct = tabletRepository.save(savedTabled);
      uploadDir = PHONE_DIR + savedTabled.getId();
    }
    saveImageDetail(mainImage, imagesDetail, fileName, uploadDir, savedProduct);
    return savedProduct;
  }

  public void saveImageDetail(
      MultipartFile mainImage,
      MultipartFile[] imagesDetail,
      String fileName,
      String uploadDir,
      Product savedProduct) {
    try {
      FileUtil.saveFile(uploadDir, fileName, mainImage);
      for (MultipartFile imageDetail : imagesDetail) {
        if (!imageDetail.isEmpty()) {
          String fileNameDetail =
              StringUtils.cleanPath(Objects.requireNonNull(imageDetail.getOriginalFilename()));
          long size = FileUtil.saveFile(uploadDir, fileNameDetail, imageDetail);
          ImageDetail productDetailImage = new ImageDetail();
          productDetailImage.setDescription("Description of " + fileNameDetail);
          productDetailImage.setProduct(savedProduct);
          productDetailImage.setName(fileNameDetail);
          productDetailImage.setSize(size);
          imageDetailService.save(productDetailImage);
        }
      }
    } catch (IOException e) {
      throw new CreateFailException("Save image filed");
    }
  }

  @Override
  public Page<ProductDto> productPage(
      int numPage, String sortField, String sortDir, int size, String keyword) {
    Pageable pageable =
        PageRequest.of(
            numPage - 1,
            size,
            sortDir.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
    Page<Product> productPage;
    if (keyword != null && !keyword.trim().equals(""))
      productPage = productRepository.search(keyword, pageable);
    else productPage = productRepository.findAll(pageable);
    return productPage.map(this::getProductDto);
  }

  //    @Override
  //    public ProductDto findById(long id) {
  //      Optional<Product> foundProduct = productRepository.findById(id);
  //      if (foundProduct.isPresent()) {
  //        return mapStructMapper.productToProductDto(foundProduct.get());
  //      } else {
  //        throw new NotFoundException("Product not found");
  //      }
  //    }

  @Override
  public ProductDto findById(long id) {
    Product foundProduct =
        productRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    return getProductDto(foundProduct);
  }

  private <T> Set<T> convertListToSet(List<T> list) {
    Set<T> set = new HashSet<>();
    for (T t : list) {
      set.add(t);
    }
    return set;
  }

  private ProductDto getProductDto(Product foundProduct) {
    ProductDto productDto = null;
    sortCategoryById(foundProduct);

    if (foundProduct instanceof Phone phone) {
      productDto = mapStructMapper.phoneToPhoneDto(phone);
      productDto.setType("phone");
    }
    if (foundProduct instanceof Laptop laptop) {
      productDto = mapStructMapper.laptopToLaptopDto(laptop);
      productDto.setType("laptop");
    }
    if (foundProduct instanceof Tablet tablet) {
      productDto = mapStructMapper.tabletToTabletDto(tablet);
      productDto.setType("tablet");
    }
    for (ImageDetailDto imageDetailDto : productDto.getImageDetailDtoSet()) {
      imageDetailDto.setProductId(productDto.getId());
      if (productDto.getType().equals("phone"))
        imageDetailDto.setUrl(
            "/images/phone-photos/" + productDto.getId() + "/" + imageDetailDto.getName());
      else if (productDto.getType().equals("laptop"))
        imageDetailDto.setUrl(
            "/images/laptop-photos/" + productDto.getId() + "/" + imageDetailDto.getName());
      else if (productDto.getType().equals("tablet"))
        imageDetailDto.setUrl(
            "/images/tablet-photos/" + productDto.getId() + "/" + imageDetailDto.getName());
    }

    Category mainCategory = getMainCategory(foundProduct.getCategorySet());

    productDto.setMainCategoryDto(mapStructMapper.categoryToCategoryDto(mainCategory));

    return productDto;
  }

  private static void sortCategoryById(Product foundProduct) {
    Set<Category> categorySet = new TreeSet<>(new CategoryComparator());
    categorySet.addAll(foundProduct.getCategorySet());
    foundProduct.setCategorySet(categorySet);
  }

  //
  @Override
  public boolean deleteProductById(long id) {
    FileUtil.deleteImageDirByProductId(id);
    productRepository.deleteProductCategoryByProductId(id);
    productRepository.deleteById(id);
    return !productRepository.existsById(id);
  }
  //
  private Category getMainCategory(Set<Category> categorySet) {
    TreeSet<Category> sortedSet = new TreeSet<>(new CategoryComparator());
    sortedSet.addAll(categorySet);
    return sortedSet.last();
  }

  public Category findRootCategory(Category category, Set<Category> categories) {
    Category currentCategory = categoryService.findById(category.getId());
    categories.add(currentCategory);
    Category parentCategory = (Category) Hibernate.unproxy(currentCategory.getParentCategory());
    if (parentCategory != null) {
      return findRootCategory(parentCategory, categories);
    } else return category;
  }
  //
  @Override
  public ProductDto findProductDtoByName(String name) {
    return mapStructMapper.productToProductDto(productRepository.findByName(name));
  }

  @Override
  public Product findByName(String name) {
    return productRepository.findByName(name);
  }

  @Override
  public Product update(ProductDto productDto, MultipartFile multipartFile) {
        Category category = mapStructMapper.categoryDtoToCategory(productDto.getMainCategoryDto());
        Set<Category> categories = new HashSet<>();
        findRootCategory(category, categories);
    if (productDto instanceof PhoneDto phoneDto) {
      updateImage(phoneDto, multipartFile, PHONE_DIR);
      Phone updatedPhone = mapStructMapper.phoneDtoToPhone(phoneDto);
            updatedPhone.setCategorySet(categories);
      return phoneRepository.save(updatedPhone);
    }
    if (productDto instanceof LaptopDto laptopDto) {
      updateImage(laptopDto, multipartFile, LAPTOP_DIR);
      Laptop updatedLaptop = mapStructMapper.laptopDtoToLaptop(laptopDto);
            updatedLaptop.setCategorySet(categories);
      return laptopRepository.save(updatedLaptop);
    }
    if (productDto instanceof TabletDto tabletDto) {
      updateImage(tabletDto, multipartFile, TABLET_DIR);
      Tablet updatedTablet = mapStructMapper.tabletDtoToTablet(tabletDto);
            updatedTablet.setCategorySet(categories);
      return tabletRepository.save(updatedTablet);
    }
    return null;
  }

  private static void updateImage(ProductDto productDto, MultipartFile multipartFile, String dir) {
    try {
      if (!multipartFile.isEmpty()) {
        String fileName =
            StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uploadDir = dir + productDto.getId();
        FileUtil.deleteFile(uploadDir, productDto.getImage());
        uploadDir = dir + productDto.getId();
        productDto.setImage(fileName);
        FileUtil.saveFile(uploadDir, fileName, multipartFile);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Transactional
  public Phone update(PhoneDto phoneDto, MultipartFile multipartFile) {
    Phone phone = mapStructMapper.phoneDtoToPhone(phoneDto);
    return phoneRepository.save(phone);
  }

  @Override
  public List<ProductDto> productDtoList() {
    List<ProductDto> productDtoList = new ArrayList<>();
    List<Product> productList = productRepository.findAll();
    for (Product product : productList) {
      productDtoList.add(getProductDto(product));
    }
    return productDtoList;
  }
}
