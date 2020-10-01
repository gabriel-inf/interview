package com.sap.coderefactoring.form;

import com.sap.coderefactoring.UserType;

import java.util.List;

public class UserForm {
    private String username;
    private String email;
    private String password;
    private String city; // This field is only populated for students type
    private List<CourseForm> completedCourses;
    private UserType type;

    public UserForm(String username, String email, String password, UserType userType) {
        username = username;
        email = email;
        password = password;
        userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        if(type==UserType.STUDENT) {
            return city;
        } else {
            return null;
        }
    }

    public void setCity(String city) throws Exception {
        if(type == UserType.INSTRUCTOR)
            throw new Exception("Instructor does not have city");
        this.city = city;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String toString() {
        return "username=" + username + ";email=" + email + ";password=" + password;
    }
}