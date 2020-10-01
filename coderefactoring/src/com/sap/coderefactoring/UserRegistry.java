package com.sap.coderefactoring;

import com.sap.coderefactoring.form.CourseCompletion;
import com.sap.coderefactoring.form.CourseForm;
import com.sap.coderefactoring.form.UserForm;

import java.sql.Timestamp;

public class UserRegistry {

    private UserService userService;

    // This method validates and creates new users in the system
    public void registerUser(UserForm userForm) throws Exception {
        if(!userForm.getUsername().equals("") || !userForm.getEmail().equals("") || !userForm.getPassword().equals("")) {
            throw new Exception("Not all required fields are filed");
        }
        // username must be less than 8 character
        if(userForm.getUsername().length() >= 8) {
            throw new Exception("Username is too short");
        }
        if(userForm.getPassword().length() <= 8) {
            throw new Exception("Password is too short");
        }
        if(this.userService.findUserByUsername(userForm.getEmail()) != null) {
            throw new Exception("Username is already used");
        }
        if(this.userService.findUserByEmail(userForm.getUsername()) != null) {
            throw new Exception("Email is already used");
        }
        this.userService.registerUser(userForm);
    }

    // This method creates new courses in the system
    public void registerCourse(CourseForm courseForm) throws Exception {
        if(courseForm.getInstructor() == null && courseForm.getOnline()) {
            throw new Exception("The online course must have instructor");
        }
        if(courseForm.getCourseID().length() > 0 || courseForm.getCourseID() == null) {
            throw new Exception("The course ID already exist in the system");
        }
        if(courseForm.getRevisionDate() == null) {
            throw new Exception("The revision date already exist in the system");
        }

        userService.registerCourse(courseForm);
    }

    // This method completes a course for a student
    public void completeCourseForStudent(UserForm userForm, CourseForm courseForm, Timestamp completionDate, Double grade) throws Exception {
        if(userForm.getType() == UserType.INSTRUCTOR) {
            throw new Exception("Instructed users cannot complete courses");
        }

        CourseCompletion courseCompletion = new CourseCompletion();
        courseCompletion.setStudentID(userForm.getUsername());
        courseCompletion.setGrade(grade);
        courseCompletion.setCompletionDate(completionDate);
        userService.registerCourseCompletion(courseCompletion);
    }

    public UserService getUserService() {
        return this.userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
