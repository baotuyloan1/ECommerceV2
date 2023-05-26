package com.bnd.ecommerce.converters;

import com.bnd.ecommerce.dto.BrandDto;
import com.bnd.ecommerce.service.BrandService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdBrandDtoToBrandDto implements Converter<String, BrandDto> {

  private final BrandService brandService;

  public IdBrandDtoToBrandDto(BrandService brandService) {
    this.brandService = brandService;
  }

  @Override
  public BrandDto convert(String id) {
    return brandService.findBrandDtoById(Integer.parseInt(id));
  }
}
