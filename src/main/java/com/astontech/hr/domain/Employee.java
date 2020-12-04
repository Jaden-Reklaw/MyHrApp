package com.astontech.hr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee extends Person{
    //region Properties
    private String background;

    @OneToMany
    private List<Project> projects;
    //endregion

    //region Constructors
    public Employee() {}
    public Employee(String firstName, String lastName, String background) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBackground(background);
    }
    //endregion

    //region Setters and Getters
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    //endregion
}
