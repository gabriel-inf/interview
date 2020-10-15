package com.sap.coderefactoring;

import com.sap.coderefactoring.form.CourseCompletion;
import com.sap.coderefactoring.form.CourseForm;
import com.sap.coderefactoring.form.UserForm;

public interface UserService {
    public UserForm findUserByUsername(String userName);
    public UserForm findUserByEmail(String userEmail);
    public boolean registerUser(UserForm userForm);

    public CourseForm findCourseById(String courseID);
    public boolean registerCourse(CourseForm userForm);

    public CourseCompletion registerCourseCompletion(CourseCompletion courseCompletion);
}
