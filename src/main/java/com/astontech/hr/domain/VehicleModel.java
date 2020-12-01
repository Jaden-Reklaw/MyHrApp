package com.astontech.hr.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleModel {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleModelId")
    private Integer id;

    @Version
    private Integer version;

    private String vehicleModelName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList;
    //endregion

    //region Constructors
    public VehicleModel() {
    }
    public VehicleModel(String vehicleModelName) {
        this.setVehicleModelName(vehicleModelName);
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

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    //endregion

    //region Methods
    public String toString() {
        //Enter fields here with getters
        return "";
    }
    //endregion
}