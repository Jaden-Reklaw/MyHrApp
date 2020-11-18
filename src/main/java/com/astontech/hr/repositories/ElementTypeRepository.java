package com.astontech.hr.repositories;

import com.astontech.hr.domain.ElementType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementTypeRepository extends CrudRepository<ElementType, Integer> {
    //Add another method to the ElementRepository Interface to implement
    //Auto generate the required code from the structure of the method name
    ElementType findByElementType(String elementType);

    List<ElementType> findAllByElementType(String elementType);
}
