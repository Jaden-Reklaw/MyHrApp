package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.repositories.VehicleModelRepository;
import com.astontech.hr.repositories.VehicleRepository;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Override
    public Iterable<VehicleModel> listAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }

    @Override
    public VehicleModel getVehicleModelById(Integer id) {
        return vehicleModelRepository.findOne(id);
    }

    @Override
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRepository.save(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelIterable) {
        return vehicleModelRepository.save(vehicleModelIterable);
    }

    @Override
    public void deleteVehicleModel(Integer id) {
        VehicleModel vehicleModel = vehicleModelRepository.findOne(id);
        // delete any vehicles of this model
        for(Vehicle vehicle: vehicleModel.getVehicleList()) {
            vehicleRepository.delete(vehicle.getId());
        }

        //remove the model from it's parents make list
        vehicleMakeService.removeVehicleModelFromMake(vehicleModel);

        vehicleModelRepository.delete(id);
    }

    @Override
    public void removeVehicleFromModel(Vehicle vehicle) {
        VehicleModel model = vehicleModelRepository.findByVehicleListContains(vehicle);
        model.getVehicleList().remove(vehicle);
        vehicleModelRepository.save(model);

    }
}
