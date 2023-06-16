package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.dto.CustomerAddressDto;
import com.bnd.ecommerce.entity.customer.CustomerAddress;
import com.bnd.ecommerce.mapper.MapStructMapper;
import com.bnd.ecommerce.repository.CustomerAddressRepository;
import com.bnd.ecommerce.service.CustomerAddressService;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

  private final MapStructMapper mapStructMapper;
  private final CustomerAddressRepository customerAddressRepository;

  public CustomerAddressServiceImpl(
      MapStructMapper mapStructMapper, CustomerAddressRepository customerAddressRepository) {
    this.mapStructMapper = mapStructMapper;
    this.customerAddressRepository = customerAddressRepository;
  }

  @Override
  public CustomerAddressDto save(CustomerAddressDto customerAddressDto) {
    CustomerAddress customerAddress =
        mapStructMapper.customerAddressDtoToCustomerAddress(customerAddressDto);
    customerAddress.setCustomer(
        mapStructMapper.customerDtoToCustomer(customerAddressDto.getCustomerDto()));
    CustomerAddress savedCustomerAddress = customerAddressRepository.save(customerAddress);
    return mapStructMapper.customerAddressToCustomerAddressDto(savedCustomerAddress);
  }
}
