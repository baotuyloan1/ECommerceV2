package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.entity.CreateTimestamp;

public class ImageDetailDto extends CreateTimestamp {

  private long id;

  private String url;

  private String name;

  private float size;

  private String description;

  private ProductDto productDto;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getSize() {
    return size;
  }

  public void setSize(float size) {
    this.size = size;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductDto getProductDto() {
    return productDto;
  }

  public void setProductDto(ProductDto productDto) {
    this.productDto = productDto;
  }

  public String getPhotoImagePath() {
    if (productDto.getId() == 0 || name == null) return null;
    if (productDto instanceof PhoneDto) {
      return "images/phone-photos/" + productDto.getId() + "/" + name;
    }
    if (productDto instanceof LaptopDto)
      return "images/laptop-photos/" + productDto.getId() + "/" + name;
    if (productDto instanceof TabletDto)
      return "images/tablet-photos/" + productDto.getId() + "/" + name;
    return null;
  }
}
