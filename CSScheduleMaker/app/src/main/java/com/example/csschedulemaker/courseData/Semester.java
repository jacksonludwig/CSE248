package com.example.csschedulemaker.courseData;

import java.util.HashMap;

public class Semester {
    private String semester;
    private CourseBag courseBag;
    private HashMap<String, Course> semCourses;

    public Semester(String semester, CourseBag courseBag) {
        this.semester = semester;
        this.courseBag = courseBag;
        semCourses = new HashMap<>();
    }

    public boolean addClass(Course course) {
        if (semCourses.containsKey(course.getCourseTitleShort())) {
            return false;
        } else {
            semCourses.put(course.getCourseTitleShort(), course);
            return true;
        }
    }

    public boolean addClass(String courseName) {
        if (semCourses.containsKey(courseName)) {
            return false;
        } else {
            semCourses.put(courseName, courseBag.get(courseName));
            return true;
        }
    }

    public Course removeClass(Course course) {
        return semCourses.remove(course.getCourseTitleShort());
    }

    public Course removeClass(String courseName) {
        return semCourses.remove(courseName);
    }

    public Course getClass(Course course) {
        return semCourses.get(course.getCourseTitleShort());
    }

    public Course getClass(String courseName) {
        return semCourses.get(courseName);
    }

    public String getSemesterName() {
        return semester;
    }

    public HashMap<String, Course> getSemCourses() {
        return semCourses;
    }

    public void setSemesterName(String semester) {
        this.semester = semester;
    }
}
