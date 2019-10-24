package com.example.csschedulemaker.courseData.initialData;

import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Utilities;

import java.io.Serializable;

public class getCSClassesAsObjectsDemo implements Serializable {

    public static void main(String[] args) {
        CourseBag courses = null;

        /*Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\cseClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\engClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\gymClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\hisClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\humClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\langClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\mathClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\scienceClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\socClasses.txt");

        Utilities.saveCourses(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat");
        */

        courses = Utilities.loadCourses("D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat");
        System.out.println(courses.get("MAT141"));
        System.out.println(courses);
    }


}
