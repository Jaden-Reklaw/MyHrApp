package com.astontech.hr.domain;

import javax.persistence.*;

@Entity
public class User {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private Integer id;

    @Version
    private Integer version;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private int age;
    //endregion

    //region Constructors
    public User() {}

    public User(String firstName, String lastName, String userName, String password, String gender, int age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setPassword(password);
        this.setGender(gender);
        this.setAge(age);
    }
    //endregion

    //region Setters and Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //endregion

    //region Methods
    public String toString() {
        //Enter fields here with getters
        return "";
    }
    //endregion
}