package com.bnd.ecommerce.converters;

import com.bnd.ecommerce.dto.ProductDto;
import com.bnd.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@Component
//public class ProductDtoConverter implements Converter<String, ProductDto> {
//
//    private final ProductService productService;
//
//    public ProductDtoConverter(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @Override
//    public ProductDto convert(String id) {
//        Object object =  productService.findById(Integer.parseInt(id));
//        return
//    }
//}
