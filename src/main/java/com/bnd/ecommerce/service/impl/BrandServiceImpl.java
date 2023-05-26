package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.BrandDto;
import com.bnd.ecommerce.entity.Brand;
import com.bnd.ecommerce.exception.DeleteFailException;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.BrandRepository;
import com.bnd.ecommerce.service.BrandService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;

  private final MapStructMapper mapStructMapper;

  public BrandServiceImpl(BrandRepository brandRepository, MapStructMapper mapStructMapper) {
    this.brandRepository = brandRepository;
    this.mapStructMapper = mapStructMapper;
  }

  @Override
  public List<Brand> brandList() {
    return brandRepository.findAll();
  }

  @Override
  public List<BrandDto> brandDtoList() {
    List<Brand> brandList = brandRepository.findAll();
    List<BrandDto> brandDtoList = new ArrayList<>();
    for (Brand brand : brandList) {
      brandDtoList.add(mapStructMapper.brandToBrandDto(brand));
    }
    return brandDtoList;
  }

  @Override
  public BrandDto findBrandDtoById(int id) {
    Optional<Brand> brand = brandRepository.findById(id);
    if (brand.isPresent()) {
      return mapStructMapper.brandToBrandDto(brand.get());
    } else {
      throw new ResourceNotFoundException("Brand Not found");
    }
  }

  @Override
  public Brand save(BrandDto brandDto) {
    Brand brand = mapStructMapper.brandDtoToBrand(brandDto);
    return brandRepository.save(brand);
  }

  @Override
  public Page<Brand> brandPage(int numPage, String sortDir, String sortField, int size) {
    Pageable pageable =
        PageRequest.of(
            numPage - 1,
            size,
            sortDir.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
    return brandRepository.findAll(pageable);
  }

  @Override
  public Brand findById(int id) {
    return brandRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
  }

  @Override
  public boolean deleteById(int id) {
    brandRepository.deleteById(id);
    if (brandRepository.existsById(id)) {
      throw new DeleteFailException("Delete brand fail");
    } else {
      return true;
    }
  }
}
