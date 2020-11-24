package com.astontech.hr.controllers;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.services.ElementTypeService;
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
public class AdminElementController {

    @Autowired
    private ElementTypeService elementTypeService;

    //Add logging
    private Logger log = Logger.getLogger(AdminElementController.class);

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/adminHome";
    }

    @RequestMapping(value = "/admin/element/add", method = RequestMethod.GET)
    public String adminElementGet(Model model) {
        model.addAttribute("elementVO", new ElementVO());
        model.addAttribute("warningAlert", "visible");
        return "admin/element/element_add";
    }

    //Values in post request are connected to the action on element_add form
    @RequestMapping(value = "/admin/element/add", method = RequestMethod.POST)
    public String adminElementPost(ElementVO elementVO, Model model) {
        elementVO.splitNewElementsIntoArray();
        logElementVO(elementVO);

        saveElementTypeAndElementsVO(elementVO);
        boolean success = true;
        if(success) {
            model.addAttribute("successAlert", "visible");
        } else {
            model.addAttribute("errorAlert", "visible");
        }

        //Clear the form
        model.addAttribute("elementVO", new ElementVO());
        return "admin/element/element_add";
    }

    @RequestMapping(value = "/admin/element/list", method = RequestMethod.GET)
    public String adminElementList(Model model) {
        //connect model to view variable for list
        model.addAttribute("elementTypeList", elementTypeService.listAllElementTypes());
        return "admin//element/element_list";
    }

    @RequestMapping(value = "/admin/element/edit/{id}", method = RequestMethod.GET)
    public String elementTypeEdit(@PathVariable int id, Model model) {
        ElementType elementType = elementTypeService.getElementTypeById(id);

        model.addAttribute("elementType", elementType);
        return "admin/element/element_edit";
    }

    @RequestMapping(value = "/admin/element/delete/{id}", method = RequestMethod.GET)
    public String elementTypeDelete(@PathVariable int id) {
        elementTypeService.deleteElementType(id);
        return "redirect:/admin/element/list";
    }

    @RequestMapping(value = "/admin/element/update", method = RequestMethod.POST)
    public String elementTypeUpdate(ElementType elementType, Model model, @RequestParam("inputNewElement") String newElement){

        // check if newElement(unbound text box) has a value, add it to the list
        if(!newElement.equals("")) {
            if(elementType.getElementList() == null) {
                List<Element> elementList = new ArrayList<>();
                elementList.add(new Element(newElement));
                elementType.setElementList(elementList);
            } else {
                elementType.getElementList().add(new Element(newElement));
            }
        }

        //iterate thru the list of elements
        for(int i=0; i < elementType.getElementList().size(); i++) {
            //check to see if element is blank
            if(elementType.getElementList().get(i).getElementName().equals("")) {
                //element name is blank remove it from the list
                elementType.getElementList().remove(i);
            }
        }

        elementTypeService.saveElementType(elementType);
        model.addAttribute("successAlert", "visible");
        return "redirect:/admin/element/edit/" + elementType.getId();
    }

    //region Helper Methods
    private void saveElementTypeAndElementsVO(ElementVO elementVO) {
        List<Element> newElementList = new ArrayList<>();
        for(String str: elementVO.getNewElementArray()) {
            newElementList.add(new Element(str));
        }

        ElementType newElementType = new ElementType(elementVO.getNewElementType());
        newElementType.setElementList(newElementList);

        elementTypeService.saveElementType(newElementType);
    }

    private void logElementVO(ElementVO elementVO) {
        log.info("New Element Type: " + elementVO.getNewElementType());
        for(String str: elementVO.getNewElementArray()) {
            log.info("New Element: " + str);
        }
    }
    //endregion
}
