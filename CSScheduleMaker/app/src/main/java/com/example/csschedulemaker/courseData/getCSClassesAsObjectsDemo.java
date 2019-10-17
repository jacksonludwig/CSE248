package com.example.csschedulemaker.courseData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class getCSClassesAsObjectsDemo {

    public static void main(String[] args) {
        CourseBag courses = new CourseBag();
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\cseClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\engClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\gymClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\hisClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\humClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\langClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\mathClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\scienceClasses.txt");
        Utilities.buildCoursesFromFile(courses, "C:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\socClasses.txt");

        System.out.println(courses);

    }


}
