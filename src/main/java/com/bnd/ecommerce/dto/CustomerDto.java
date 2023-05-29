package com.bnd.ecommerce.dto;

import com.bnd.ecommerce.enums.GenderEnum;
import com.bnd.ecommerce.validator.email.UniqueEmailCustomer;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class CustomerDto {

    private long id;

    @NotNull
    private String firstName;

    @NotNull private String lastName;

    @UniqueEmailCustomer
    private String email;

    private String phone;

    private String password;

    private Set<CustomerAddressDto> customerAddressDtoSet;

    private GenderEnum genderEnum;

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CustomerAddressDto> getCustomerAddressDtoSet() {
        return customerAddressDtoSet;
    }

    public void setCustomerAddressDtoSet(Set<CustomerAddressDto> customerAddressDtoSet) {
        this.customerAddressDtoSet = customerAddressDtoSet;
    }
}
