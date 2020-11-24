package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleModel;

public interface VehicleModelService {
    Iterable<VehicleModel> listAllVehicleModels();

    VehicleModel getVehicleModelById(Integer id);

    VehicleModel saveVehicleModel(VehicleModel vehicleModel);

    Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable);

    void deleteVehicleModel(Integer id);
}
