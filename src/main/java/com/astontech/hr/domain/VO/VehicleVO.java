package com.astontech.hr.domain.VO;

//VO Stands for Value Object and are used to collect input from the views
//that will eventaully be sent to the database
public class VehicleVO {
    //region Fields
    private String newVehicleMake;
    private String newVehicleModels;
    private String[] newVehicleModelArray;
    //endregion

    //region Constructors
    public VehicleVO() {}
    //endregion

    //region Setters and Getters
    public String getNewVehicleMake() {
        return newVehicleMake;
    }

    public void setNewVehicleMake(String newVehicleMake) {
        this.newVehicleMake = newVehicleMake;
    }

    public String getNewVehicleModels() {
        return newVehicleModels;
    }

    public void setNewVehicleModels(String newVehicleModels) {
        this.newVehicleModels = newVehicleModels;
    }

    public String[] getNewVehicleModelArray() {
        return newVehicleModelArray;
    }

    public void setNewVehicleModelArray(String[] newVehicleModelArray) {
        this.newVehicleModelArray = newVehicleModelArray;
    }
    //endregion

    //region Methods
    //regex for splitting on a new line or carriage returns is "\\r?\\n"
    public void splitVehicleModelsIntoArray() {
        this.setNewVehicleModelArray(this.newVehicleModels.split("\\r?\\n"));
    }


    //endregion
}
