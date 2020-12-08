package com.astontech.hr.domain;

import javax.persistence.*;

@Entity
public class Address {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AddressId")
    private Integer id;

    @Version
    private Integer version;

    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String typeAddress;
    //endregion

    //region Constructors
    public Address() {}
    public Address(String street, String city, String state, String zipcode, String typeAddress) {
        this.setStreet(street);
        this.setCity(city);
        this.setState(state);
        this.setZipcode(zipcode);
        this.setTypeAddress(typeAddress);
    }
    //endregion

    //region Setters and Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(String typeAddress) {
        this.typeAddress = typeAddress;
    }
    //endregion

    //region Methods
    public String toString() {
        //Enter fields here with getters
        return "";
    }
    //endregion
}