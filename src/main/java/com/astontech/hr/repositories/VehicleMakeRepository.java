package com.astontech.hr.repositories;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

public interface VehicleMakeRepository extends CrudRepository<VehicleMake, Integer> {
    VehicleMake findByVehicleMakeName(String vehicleMakeName);

    VehicleMake findByVehicleModelListContains(VehicleModel vehicleModel);
}
