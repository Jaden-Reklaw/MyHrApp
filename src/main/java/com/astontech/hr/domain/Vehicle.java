package com.astontech.hr.domain;

import javax.persistence.*;

@Entity
public class Vehicle {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleId")
    private Integer id;

    @Version
    private Integer version;

    private int vehicleYear;
    private String vehiclePlate;
    private String VIN;
    private String Color;
    //endregion

    //region Constructors
    public Vehicle() {
    }

    public Vehicle(int vehicleYear, String vehiclePlate) {
        this.setVehicleYear(vehicleYear);
        this.setVehiclePlate(vehiclePlate);
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

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
    //endregion

    //region Methods
    public String toString() {
        //Enter fields here with getters
        return "";
    }
    //endregion
}