package com.example.csschedulemaker.courseData;

import java.io.Serializable;
import java.util.HashMap;

public class CourseBag implements Serializable {
    private HashMap<String, Course> cseCourses;

    private static final long serialVersionUID = -8263569645527485974L;

    public CourseBag() {
        cseCourses = new HashMap<>();
    }

    public boolean insert(Course course) {
        if (cseCourses.containsKey(course.getCourseTitleShort())) {
            return false;
        } else {
            cseCourses.put(course.getCourseTitleShort(), course);
            return true;
        }
    }

    public Course get(String key) {
        return cseCourses.get(key);
    }

    public Course delete(String key) {
        return cseCourses.remove(key);
    }

    public boolean update(String key, Course course) {
        if (cseCourses.containsKey(key)) {
            cseCourses.remove(key);
            cseCourses.put(key, course);
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return cseCourses.size();
    }

    public HashMap<String, Course> getCourseHash() {
        return cseCourses;
    }


    @Override
    public String toString() {
        return "CourseBag{" +
                "cseCourses=" + cseCourses +
                '}';
    }
}
