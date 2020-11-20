package com.astontech.hr.domain.VO;

//VO Stands for Value Object and are used to collect input from the views
//that will eventaully be sent to the database
public class ElementVO {
    //region Fields
    private String newElementType;
    private String newElements;
    private String[] newElementArray;
    //endregion

    //region Constructors
    public ElementVO() {}
    //endregion

    //region Setters and Getters
    public String getNewElementType() {
        return newElementType;
    }

    public void setNewElementType(String newElementType) {
        this.newElementType = newElementType;
    }

    public String getNewElements() {
        return newElements;
    }

    public void setNewElements(String newElements) {
        this.newElements = newElements;
    }

    public String[] getNewElementArray() {
        return newElementArray;
    }

    public void setNewElementArray(String[] newElementArray) {
        this.newElementArray = newElementArray;
    }
    //endregion

    //region Methods
    //regex for splitting on a new line or carriage returns is "\\r?\\n"
    public void splitNewElementsIntoArray() {
        this.setNewElementArray(this.newElements.split("\\r?\\n"));
    }
    //endregion
}
