package com.bnd.ecommerce.mapper;

import com.bnd.ecommerce.dto.*;
import com.bnd.ecommerce.entity.*;
import com.bnd.ecommerce.entity.employee.Employee;
import com.bnd.ecommerce.entity.stock.Stock;
import com.bnd.ecommerce.entity.stock.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

  EmployeeUpdateDto employeeToEmployeePostDto(Employee employee);

  Employee employeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);

  Employee employeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto);

  Brand brandDtoToBrand(BrandDto brandDto);

  BrandDto brandToBrandDto(Brand brand);

  @Mapping(source = "brand", target = "brandDto")
  @Mapping(target = "imageDetailDtoSet", source = "imageDetailSet")
  ProductDto productToProductDto(Product product);

  Category categoryDtoToCategory(CategoryDto categoryDto);

  CategoryDto categoryToCategoryDto(Category category);

  @Mapping(source = "imageDetailDtoSet", target = "imageDetailSet")
  @Mapping(source = "brandDto", target = "brand")
  Product productDtoToProduct(ProductDto productDto);

  PhoneDto phoneToPhoneDto(Phone phone);

  @Mapping(source = "brandDto", target = "brand")
  Phone phoneDtoToPhone(PhoneDto phoneDto);

  ImageDetail imageDetailDtoToImageDetail(ImageDetailDto imageDetailDto);

  ImageDetailDto imageDetailToImageDetailDto(ImageDetail imageDetail);

  Stock stockDtoToStock(StockDto stockDto);

  StockDto stockToStockDto(Stock stock);

  Warehouse wareHouseDtoToWareHouse(WarehouseDto warehouseDto);

  WarehouseDto wareHouseToWareHouseDto(Warehouse warehouse);

  LaptopDto laptopToLaptopDto(Laptop laptop);

  Laptop laptopDtoToLaptop(LaptopDto laptopDto);

  Tablet tabletDtoToTablet(TabletDto tabletDto);
}
