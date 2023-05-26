package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.WarehouseDto;
import com.bnd.ecommerce.entity.stock.Warehouse;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.WareHouseRepository;
import com.bnd.ecommerce.service.WareHouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WareHouseServiceImpl implements WareHouseService {
  private final WareHouseRepository wareHouseRepository;

  private final MapStructMapper mapStructMapper;

  public WareHouseServiceImpl(
      WareHouseRepository wareHouseRepository, MapStructMapper mapStructMapper) {
    this.wareHouseRepository = wareHouseRepository;
    this.mapStructMapper = mapStructMapper;
  }

  @Override
  public Warehouse save(WarehouseDto warehouseDto) {
    Warehouse warehouse = mapStructMapper.wareHouseDtoToWareHouse(warehouseDto);
    return wareHouseRepository.save(warehouse);
  }

  @Override
  public Page<Warehouse> pageWareHouse(
      int pageNum, String sortField, String sortDir, int size, String keyword) {
    Pageable pageable =
        PageRequest.of(
            pageNum - 1,
            size,
            sortDir.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
    if (keyword != null && !keyword.trim().equals("")) {
      return null;
    }
    return wareHouseRepository.findAll(pageable);
  }

  @Override
  public WarehouseDto findWarehouseDtoById(int id) {
    Optional<Warehouse> warehouse = wareHouseRepository.findById(id);
    if(warehouse.isPresent()){
      return mapStructMapper.wareHouseToWareHouseDto(warehouse.get());
    }else throw new ResourceNotFoundException("Warehouse not found");
  }

  @Override
  public boolean deleteById(int id) {
    wareHouseRepository.deleteById(id);
    return !wareHouseRepository.existsById(id);
  }

  @Override
  public List<Warehouse> warehouseList() {
    return wareHouseRepository.findAll();
  }
}
