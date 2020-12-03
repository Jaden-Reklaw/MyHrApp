package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //generateElementAndElementTypes();
    }

    private void generateElementAndElementTypes() {
        ElementType laptopType = new ElementType("Laptop");

        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Acer"));
        elementList.add(new Element("HP"));
        elementList.add(new Element("Dell"));
        elementList.add(new Element("Apple"));

        laptopType.setElementList(elementList);

        elementTypeService.saveElementType(laptopType);

        //Email
        ElementType emailType = new ElementType("Email");

        List<Element> emailList = new ArrayList<>();
        emailList.add(new Element("Work"));
        emailList.add(new Element("Personal"));
        emailList.add(new Element("Delegated"));

        emailType.setElementList(emailList);

        elementTypeService.saveElementType(emailType);

        //Vehicle Makes and Vehicle Models
        VehicleMake mazda = new VehicleMake("Mazda");
        List<VehicleModel> mazdaList = new ArrayList<>();
        mazdaList.add(new VehicleModel("CX-3"));
        mazdaList.add(new VehicleModel("CX-5"));
        mazdaList.add(new VehicleModel("CX-7"));
        mazdaList.add(new VehicleModel("CX-9"));
        mazda.setVehicleModelList(mazdaList);
        vehicleMakeService.saveVehicleMake(mazda);

        VehicleMake ford = new VehicleMake("Ford");
        List<VehicleModel> fordList = new ArrayList<>();
        fordList.add(new VehicleModel("Explorer"));
        fordList.add(new VehicleModel("Fusion"));
        ford.setVehicleModelList(fordList);
        vehicleMakeService.saveVehicleMake(ford);
    }
}
