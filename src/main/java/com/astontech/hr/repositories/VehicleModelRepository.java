package com.astontech.hr.repositories;

import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

public interface VehicleModelRepository extends CrudRepository<VehicleModel, Integer> {
    VehicleModel findByVehicleModelName(String vehicleModelName);

    VehicleModel findByVehicleListContains(Vehicle vehicle);
}
