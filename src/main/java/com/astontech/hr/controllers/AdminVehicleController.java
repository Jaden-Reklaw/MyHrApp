package com.astontech.hr.controllers;

import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminVehicleController {
    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleModelService vehicleModelService;

    //Add logging
    private Logger log = Logger.getLogger(AdminElementController.class);

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminVehicleMakeGet(Model model) {
        model.addAttribute("vehicleVO", new VehicleVO());
        model.addAttribute("warningAlert", "visible");
        return "admin/vehicle/vehicle_add";
    }

    //Values in post request are connected to the action on element_add form
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String adminVehicleMakePost(VehicleVO vehicleVO, Model model) {
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

    @RequestMapping(value = "/admin/vehicle/make/list", method = RequestMethod.GET)
    public String adminVehicleMakeList(Model model) {
        //connect model to view variable for list
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMakes());
        return "admin/vehicle/vehicle_list";
    }

    @RequestMapping(value = "/admin/vehicle/model/list", method = RequestMethod.GET)
    public String adminVehicleModelList(Model model) {
        //connect model to view variable for list
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModels());
        return "admin/vehicle/vehicle_model_list";
    }

    @RequestMapping(value = "/admin/vehicle/make/edit/{id}", method = RequestMethod.GET)
    public String vehicleMakeEdit(@PathVariable int id, Model model) {
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(id);

        model.addAttribute("vehicleMake", vehicleMake);
        return "admin/vehicle/vehicle_make_edit";
    }

    @RequestMapping(value = "/admin/vehicle/model/edit/{id}", method = RequestMethod.GET)
    public String vehicleModelEdit(@PathVariable int id, Model model) {
        VehicleModel vehicleModel = vehicleModelService.getVehicleModelById(id);

        model.addAttribute("vehicleModel", vehicleModel);
        return "admin/vehicle/vehicle_model_edit";
    }

    @RequestMapping(value = "/admin/vehicle/make/delete/{id}", method = RequestMethod.GET)
    public String vehicleMakeDelete(@PathVariable int id) {
        vehicleMakeService.deleteVehicleMake(id);
        return "redirect:/admin/vehicle/make/list";
    }

    @RequestMapping(value = "/admin/vehicle/model/delete/{id}", method = RequestMethod.GET)
    public String vehicleModelDelete(@PathVariable(value = "id") int modelId) {
        vehicleModelService.deleteVehicleModel(modelId);
        return "redirect:/admin/vehicle/model/list";
    }

    @RequestMapping(value = "/admin/vehicle/make/update", method = RequestMethod.POST)
    public String vehicleMakeUpdate(VehicleMake vehicleMake, Model model, @RequestParam("inputNewVehicleModel") String newVehicleModel){

        // check if newVehicleModel(unbound text box) has a value, add it to the list
        if(!newVehicleModel.equals("")) {
            if(vehicleMake.getVehicleModelList() == null) {
                List<VehicleModel> vehicleModelList = new ArrayList<>();
                vehicleModelList.add(new VehicleModel(newVehicleModel));
                vehicleMake.setVehicleModelList(vehicleModelList);
            } else {
                vehicleMake.getVehicleModelList().add(new VehicleModel(newVehicleModel));
            }
        }

        VehicleModel vehicleModel = null;
        //iterate thru the list of elements
        for(int i=0; i < vehicleMake.getVehicleModelList().size(); i++) {
            //check to see if element is blank
            if(vehicleMake.getVehicleModelList().get(i).getVehicleModelName().equals("")) {
                //element name is blank remove it from the list
                vehicleModel = vehicleMake.getVehicleModelList().get(i);
            }
        }

        vehicleMakeService.saveVehicleMake(vehicleMake);
        if(vehicleModel != null) {
            vehicleModelService.deleteVehicleModel(vehicleModel.getId());
        }
        model.addAttribute("successAlert", "visible");
        return "redirect:/admin/vehicle/make/edit/" + vehicleMake.getId();
    }

    @RequestMapping(value = "/admin/vehicle/model/update", method = RequestMethod.POST)
    public String vehicleModelUpdate(VehicleModel vehicleModel, Model model, @RequestParam("inputNewVehiclePlate") String newVehicle){

        // check if newVehicle(unbound text box) has a value, add it to the list
        if(!newVehicle.equals("")) {
            if(vehicleModel.getVehicleList() == null) {
                List<Vehicle> vehicleList = new ArrayList<>();
                vehicleList.add(new Vehicle(newVehicle));
                vehicleModel.setVehicleList(vehicleList);
            } else {
                vehicleModel.getVehicleList().add(new Vehicle(newVehicle));
            }
        }

        //iterate thru the list of elements
        for(int i=0; i < vehicleModel.getVehicleList().size(); i++) {
            //check to see if element is blank
            if(vehicleModel.getVehicleList().get(i).getVehiclePlate().equals("")) {
                //element name is blank remove it from the list
                vehicleModel.getVehicleList().remove(i);
            }
        }

        vehicleModelService.saveVehicleModel(vehicleModel);
        model.addAttribute("successAlert", "visible");
        return "redirect:/admin/vehicle/model/edit/" + vehicleModel.getId();
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
        log.info("New Vehicle Make: " + vehicleVO.getNewVehicleMake());
        for(String str: vehicleVO.getNewVehicleModelArray()) {
            log.info("New Vehicle Model: " + str);
        }
    }
    //endregion
}
