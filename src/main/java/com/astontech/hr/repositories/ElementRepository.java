package com.astontech.hr.repositories;

import com.astontech.hr.domain.Element;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementRepository extends CrudRepository<Element, Integer> {

    //Add another method to the ElementRepository Interface to implement
    //Auto generate the required code from the structure of the method name
    Element findByElementName(String elementName);

    //Get back a list of elements by element name
    List<Element> findAllByElementName(String elementName);

    List<Element> findByElementNameNot(String elementName);

    List<Element> findByElementNameIgnoreCase(String elementName);
}
