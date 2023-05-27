package com.bnd.ecommerce.mapper;

import com.bnd.ecommerce.dto.*;
import com.bnd.ecommerce.entity.*;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.entity.stock.Warehouse;
import java.util.Set;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

  EmployeeUpdateDto employeeToEmployeePostDto(Employee employee);

  Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);

  Employee employeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto);

  Brand brandDtoToBrand(BrandDto brandDto);

  BrandDto brandToBrandDto(Brand brand);


  @InheritInverseConfiguration(name = "productDtoToProduct")
  ProductDto productToProductDto(Product product);

  Category categoryDtoToCategory(CategoryDto categoryDto);

  CategoryDto categoryToCategoryDto(Category category);

  @Mapping(source = "categoryDtoSet", target = "categorySet")
  @Mapping(source = "imageDetailDtoSet", target = "imageDetailSet")
  @Mapping(source = "brandDto", target = "brand")
  Product productDtoToProduct(ProductDto productDto);

  @InheritInverseConfiguration(name = "phoneDtoToPhone")
  PhoneDto phoneToPhoneDto(Phone phone);

  @InheritConfiguration(name = "productDtoToProduct")
  Phone phoneDtoToPhone(PhoneDto phoneDto);

  @Mapping(source = "productDto", target = "product", ignore = true)
  ImageDetail imageDetailDtoToImageDetail(ImageDetailDto imageDetailDto);

  @InheritInverseConfiguration(name = "imageDetailDtoToImageDetail")
  ImageDetailDto imageDetailToImageDetailDto(ImageDetail imageDetail);

  Stock stockDtoToStock(StockDto stockDto);

  StockDto stockToStockDto(Stock stock);

  Warehouse wareHouseDtoToWareHouse(WarehouseDto warehouseDto);

  WarehouseDto wareHouseToWareHouseDto(Warehouse warehouse);

  //  @Mapping(source = "categorySet", target = "categoryDtoSet")
  //  @Mapping(source = "brand", target = "brandDto")
  //  @Mapping(source = "imageDetailSet", target = "imageDetailDtoSet")
  //  @Mapping(source = "stockSet",target = "stockDtoSet")
  LaptopDto laptopToLaptopDto(Laptop laptop);

  //  @Mapping(source = "categoryDtoSet", target = "categorySet")
  //  @Mapping(source = "imageDetailDtoSet", target = "imageDetailSet")
  //  @Mapping(source = "brandDto", target = "brand")
  //  @Mapping(source = "stockDtoSet",target = "stockSet")
  Laptop laptopDtoToLaptop(LaptopDto laptopDto);

  Tablet tabletDtoToTablet(TabletDto tabletDto);

  TabletDto tabletToTabletDto(Tablet tablet);

  Set<Category> categoryDtoSetToCategorySet(Set<CategoryDto> categoryDtoSet);

  Set<CategoryDto> categorySetToCategoryDtoSet(Set<Category> categorySet);
}
