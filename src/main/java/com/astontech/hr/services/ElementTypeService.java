package com.astontech.hr.services;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;

import java.util.List;

public interface ElementTypeService {
    Iterable<ElementType> listAllElementTypes();

    ElementType getElementTypeById(Integer id);

    ElementType saveElementType(ElementType elementType);

    Iterable<ElementType> saveElementTypeList(Iterable<ElementType> elementTypeIterable);

    void deleteElementType(Integer id);

    //Custom Repo Methods
    ElementType findByElementType(String elementType);

    List<ElementType> findAllByElementType(String elementType);
}
