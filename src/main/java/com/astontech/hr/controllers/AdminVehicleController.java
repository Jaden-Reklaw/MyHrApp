package com.astontech.hr.controllers;

import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.services.VehicleMakeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminVehicleController {
    @Autowired
    private VehicleMakeService vehicleMakeService;

    //Add logging
    private Logger log = Logger.getLogger(AdminElementController.class);

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminElementGet(Model model) {
        model.addAttribute("vehicleVO", new VehicleVO());
        model.addAttribute("warningAlert", "visible");
        return "admin/vehicle/vehicle_add";
    }

    //Values in post request are connected to the action on element_add form
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String adminElementPost(VehicleVO vehicleVO, Model model) {
        vehicleVO.splitVehicleModelsIntoArray();
        logElementVO(vehicleVO);

        saveVehicleMakeAndVehicleVO(vehicleVO);
        boolean success = true;
        if(success) {
            model.addAttribute("successAlert", "visible");
        } else {
            model.addAttribute("errorAlert", "visible");
        }

        //Clear the form
        model.addAttribute("vehicleVO", new VehicleVO());
        return "admin/vehicle/vehicle_add";
    }

    //region Custom Helper Methods
    private void saveVehicleMakeAndVehicleVO(VehicleVO vehicleVO) {
        List<VehicleModel> newVehicleModelList = new ArrayList<>();
        for(String str: vehicleVO.getNewVehicleModelArray()) {
            newVehicleModelList.add(new VehicleModel(str));
        }

        VehicleMake newVehicleMake = new VehicleMake(vehicleVO.getNewVehicleMake());
        newVehicleMake.setVehicleModelList(newVehicleModelList);

        vehicleMakeService.saveVehicleMake(newVehicleMake);
    }

    private void logElementVO(VehicleVO vehicleVO) {
        log.info("New Element Type: " + vehicleVO.getNewVehicleMake());
        for(String str: vehicleVO.getNewVehicleModelArray()) {
            log.info("New Element: " + str);
        }
    }
    //endregion
}
