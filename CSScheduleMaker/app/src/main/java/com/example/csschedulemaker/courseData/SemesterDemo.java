package com.example.csschedulemaker.courseData;

public class SemesterDemo {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        CourseBag courseBag = Utilities.loadCourses(path + "\\app\\src\\main\\java\\com\\example\\csschedulemaker\\courseData\\courseData.dat");
        System.out.println(courseBag);
        Semester fall = new Semester("fall 2019", courseBag);

        // fall.addClass("CSE110");
        System.out.println(fall.getSemesterName());
        System.out.println(fall.getClass("CSE110"));
    }

}
