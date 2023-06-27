package com.bnd.ecommerce.controller.raw;

import com.bnd.ecommerce.dto.BrandDto;
import com.bnd.ecommerce.entity.Brand;
import com.bnd.ecommerce.exception.CreateFailException;
import com.bnd.ecommerce.exception.UpdateFailException;
import com.bnd.ecommerce.service.BrandService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rawUI/brands")
@Validated
public class BrandController {
  private final BrandService brandService;

  private static final String REDIRECT_BRANDS = "redirect:/rawUI/brands/1";

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping("/{numPage}")
  public String showBrands(
      @PathVariable("numPage") @Positive int numPage,
      @RequestParam(value = "numberElement", defaultValue = "10") @Positive int numberElement,
      @RequestParam(value = "sortField", defaultValue = "id") String sortField,
      @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
      Model model,
      Authentication authentication) {
    Page<Brand> brandPage = brandService.brandPage(numPage, sortDir, sortField, numberElement);
    model.addAttribute("listBrands", brandPage.getContent());
    model.addAttribute("totalPages", brandPage.getTotalPages());
    model.addAttribute("currentPage", numPage);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    model.addAttribute("numberElement", numberElement);
    List<String> currentRoles = getStringRoles(authentication);
    model.addAttribute("currentRoles", currentRoles);
    return "rawUI/brand/brands";
  }

  private static List<String> getStringRoles(Authentication authentication) {
    List<String> currentRoles = new LinkedList<>();
    for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
      currentRoles.add(grantedAuthority.getAuthority());
    }
    return currentRoles;
  }

  @GetMapping("/createBrand")
  public String showNewBrand(Model model) {
    BrandDto brandDto = new BrandDto();
    model.addAttribute("brand", brandDto);
    return "rawUI/brand/new_brand";
  }

  @PostMapping("/saveBrand")
  public String saveBrand(Model model, @ModelAttribute("brand") BrandDto brandDto) {
    Brand savedBrand = brandService.save(brandDto);
    if (savedBrand != null) {
      return REDIRECT_BRANDS;
    } else throw new CreateFailException("Create Brand Failed");
  }

  @PostMapping("/updateBrand/{id}")
  public String updateBrand(
      Model model,
      @ModelAttribute("brand") BrandDto brandDto,
      @PathVariable("id") @Positive int id) {
    brandDto.setId(id);
    Brand updatedBrand = brandService.save(brandDto);
    if (updatedBrand != null) {
      return REDIRECT_BRANDS;
    } else throw new UpdateFailException("Update Brand Failed");
  }

  @GetMapping("/editBrand/{id}")
  public String showEditBrandPage(Model model, @PathVariable("id") @Positive int id) {
    Brand foundBrand = brandService.findById(id);
    model.addAttribute("brand", foundBrand);
    return "rawUI/brand/edit_brand";
  }

  @GetMapping("/deleteBrand/{id}")
  public String deleteBrand(@PathVariable("id") @Positive int id) {
    brandService.deleteById(id);
    return REDIRECT_BRANDS;
  }
}
