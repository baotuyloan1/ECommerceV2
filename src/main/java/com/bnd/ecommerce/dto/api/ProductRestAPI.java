package com.bnd.ecommerce.dto.api;

import com.bnd.ecommerce.entity.Laptop;
import com.bnd.ecommerce.entity.Phone;
import com.bnd.ecommerce.entity.Tablet;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public class ProductRestAPI {

    private long id;

    private String name;
    private String description;
    private double price;

    private String brand;

    private Phone phone;
    private Laptop laptop;
    private Tablet tablet;


    public Set<CategoryRestAPI> getCategoryRestAPISet() {
        return categoryRestAPISet;
    }

    public void setCategoryRestAPISet(Set<CategoryRestAPI> categoryRestAPISet) {
        this.categoryRestAPISet = categoryRestAPISet;
    }

    @JsonProperty("categories")
    private Set<CategoryRestAPI> categoryRestAPISet;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public void setTablet(Tablet tablet) {
        this.tablet = tablet;
    }




}
