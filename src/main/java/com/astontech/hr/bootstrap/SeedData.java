package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.*;
import com.astontech.hr.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private AddressService addressService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateElementAndElementTypes();
        generateVehicleModelMake();
        generateEmployees();
        generateContactAndAddress();
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
    }

    private void generateVehicleModelMake() {
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

    private void generateEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Jordan");
        employee1.setLastName("Walker");
        employee1.setBackground("Java Developer");
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Kelsey");
        employee2.setLastName("Walker");
        employee2.setBackground("Nurse");
        employeeService.saveEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Axel");
        employee3.setLastName("Walker");
        employee3.setBackground("Rock Star");
        employeeService.saveEmployee(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Wayne");
        employee4.setLastName("Henderson");
        employee4.setBackground("Project Manager");
        employeeService.saveEmployee(employee4);
    }

    private void generateContactAndAddress() {
        //Create a contact
        Contact newContact = new Contact("Bob", "Belcher", "Bob's Burgers");
        List<Address> allAddresses = new ArrayList<>();
        Address address1 = new Address("123 Main St. Suite 1", "Connie Island", "NJ", "86753", "Home");
        Address address2 = new Address("123 Main St. Suite 2", "Connie Island", "NJ", "86753", "Home");
        allAddresses.add(address1);
        allAddresses.add(address2);
        newContact.setAddresses(allAddresses);
        contactService.saveContact(newContact);
    }
}
