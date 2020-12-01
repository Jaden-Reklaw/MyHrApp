package com.astontech.hr.domain.VO;

public class UserVO {
    //region Fields
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private String age;


    //endregion

    //region Constructors
    public UserVO() {}


    //endregion

    //region Setters and Getters
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    //endregion

    //region Methods

    //endregion
}
