package com.example.csschedulemaker.courseData.initialData;

import com.example.csschedulemaker.courseData.CourseBag;
import com.example.csschedulemaker.courseData.Utilities;

import java.io.Serializable;

public class getCSClassesAsObjectsDemo implements Serializable {

    public static void main(String[] args) {
        CourseBag courses = new CourseBag();

        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\cseClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\engClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\gymClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\hisClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\humClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\langClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\mathClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\scienceClasses.txt");
        Utilities.buildCoursesFromFile(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\initialData\\socClasses.txt");

        Utilities.saveCourses(courses, "D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat");


        courses = Utilities.loadCourses("D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat");
        System.out.println(courses.get("MAT141"));
        System.out.println(courses);
    }


}
