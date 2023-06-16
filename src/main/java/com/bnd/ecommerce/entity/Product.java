package com.bnd.ecommerce.entity;

import com.bnd.ecommerce.entity.stock.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product extends CreateUpdateTimeStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 512)
  @NotBlank(message = "Product name cannot blank")
  @Length(min = 5, max = 512, message = "Product name must be between 5-512 characters")
  private String name;

  private String description;

  @Min(value = 10, message = "Product price must be greater or equal to 10")
  @Max(value = 50000, message = "Product price must be less than or equal to 50000")
  private float price;

  @ManyToOne private Brand brand;

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.product")
  public Set<Stock> stockSet = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "product_category",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  @JsonIgnoreProperties({"children","products"})
  private Set<Category> categorySet;

  private String image;

  public Set<Stock> getStockSet() {
    return stockSet;
  }

  public void setStockSet(Set<Stock> stockSet) {
    this.stockSet = stockSet;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void addImageDetail(ImageDetail imageDetail) {
    if (imageDetailSet == null) imageDetailSet = new HashSet<>();
    imageDetailSet.add(imageDetail);
  }

  @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<ImageDetail> imageDetailSet;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }



  public Set<Category> getCategorySet() {
    return categorySet;
  }

  public void setCategorySet(Set<Category> categories) {
    this.categorySet = categories;
  }

  public Set<ImageDetail> getImageDetailSet() {
    return imageDetailSet;
  }

  public void setImageDetailSet(Set<ImageDetail> imageDetails) {
    this.imageDetailSet = imageDetails;
  }

  public void addCategory(Category category) {
    if (categorySet == null) categorySet = new HashSet<>();
    this.categorySet.add(category);
  }
}
