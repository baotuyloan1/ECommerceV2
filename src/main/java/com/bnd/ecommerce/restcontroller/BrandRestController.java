package com.bnd.ecommerce.restcontroller;

import com.bnd.ecommerce.dto.BrandDto;
import com.bnd.ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandRestController {

    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<BrandDto>> listAll(){
        return ResponseEntity.ok(brandService.brandDtoList());
    }
}
