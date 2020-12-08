package com.astontech.hr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact extends Person{
    //region Fields
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
    //endregion

    //region Constructors
    public Contact() {}
    public  Contact(String firstName, String lastName, String companyName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCompanyName(companyName);
    }
    //endregion

    //region Setters and Getters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    //endregion

    //region Methods
    public String toString() {
        //Enter fields here with getters
        return "";
    }
    //endregion
}