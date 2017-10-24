package com.mohammadmirzakhani.courses.interfaces;

import com.mohammadmirzakhani.courses.model.Course;

import java.util.Collection;

public interface CourseService {
    void addCourse(Course course);

    Collection<Course> getCourses();

    Course getCourse(int id);

    Course editCourse(Course course);

    void deleteCourse(int id);

    boolean CourseExist(int id);
}
