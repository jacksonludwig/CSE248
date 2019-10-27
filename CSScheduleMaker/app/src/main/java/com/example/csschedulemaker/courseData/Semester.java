package com.example.csschedulemaker.courseData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

public class Semester implements Serializable {
    private String semester;
    private CourseBag courseBag;
    // private HashMap<String, Course> semCourses;
    private ArrayList<Course> semCourses;

    private int numClasses;

    public Semester(String semester, CourseBag courseBag) {
        this.semester = semester;
        this.courseBag = courseBag;
        semCourses = new ArrayList<>();
        numClasses = 0;
    }

    public boolean addClass(Course course) {
        if (semCourses.contains(course)) {
            return false;
        } else {
            semCourses.add(course);
            numClasses++;
            return true;
        }
    }

    /*
    public boolean addClass(String courseName) {
        if (semCourses.containsKey(courseName)) {
            return false;
        } else {
            semCourses.put(courseName, courseBag.get(courseName));
            numClasses++;
            return true;
        }
    }
    */

    public Boolean removeClass(Course course) {
        numClasses--;
        return semCourses.remove(course);
    }

    /*
    public Course removeClass(String courseName) {
        numClasses--;
        return semCourses.remove(courseName);
    }
    */

    public Course getClass(String courseName) {
        for (Course c : semCourses) {
            if (c.getCourseTitleShort().equalsIgnoreCase(courseName)) {
                return c;
            }
        }
        return null;
    }

    /*
    public Course getClass(String courseName) {
        return semCourses.get(courseName);
    }
    */

    public String getSemesterName() {
        return semester;
    }


    public ArrayList<Course> getSemCourses() {
        return semCourses;
    }


    public CourseBag getCourseBag() {
        return courseBag;
    }

    public void setCourseBag(CourseBag courseBag) {
        this.courseBag = courseBag;
    }

    public int getNumClasses() {
        return numClasses;
    }

    public static Comparator<Semester> semYearComparator = new Comparator<Semester>() {

        public int compare(Semester s1, Semester s2) {
            String semester1 = s1.getSemesterName().toUpperCase();
            String semester2 = s2.getSemesterName().toUpperCase();

            String sem1YearOnly = semester1.replaceAll("[^0-9]", "");
            String sem2YearOnly = semester2.replaceAll("[^0-9]", "");

            return sem1YearOnly.compareTo(sem2YearOnly);
        }
    };

    public static Comparator<Semester> semSeasonComparator = new Comparator<Semester>() {

        public int compare(Semester s1, Semester s2) {
            String semester1 = s1.getSemesterName().toUpperCase();
            String semester2 = s2.getSemesterName().toUpperCase();

            String sem1SeasonOnly = semester1.replaceAll("\\d", "");
            sem1SeasonOnly = sem1SeasonOnly.replaceAll("\\s+", "");
            String sem2SeasonOnly = semester2.replaceAll("\\d", "");
            sem2SeasonOnly = sem2SeasonOnly.replaceAll("\\s+", "");

            int winterWeight = 1;
            int fallWeight = 4;
            int springWeight = 2;
            int summerWeight = 3;
            int sem1Weight = -1;
            int sem2Weight = -1;

            switch (sem1SeasonOnly) {
                case "FALL":
                    sem1Weight = fallWeight;
                    break;
                case "WINTER":
                    sem1Weight = winterWeight;
                    break;
                case "SPRING":
                    sem1Weight = springWeight;
                    break;
                case "SUMMER":
                    sem1Weight = summerWeight;
                    break;
                default:
                    break;
            }

            switch (sem2SeasonOnly) {
                case "FALL":
                    sem2Weight = fallWeight;
                    break;
                case "WINTER":
                    sem2Weight = winterWeight;
                    break;
                case "SPRING":
                    sem2Weight = springWeight;
                    break;
                case "SUMMER":
                    sem2Weight = summerWeight;
                    break;
                default:
                    break;
            }

            return sem1Weight - sem2Weight;
        }
    };

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < semCourses.size(); i++) {
            returnString = returnString + semCourses.get(i).getCourseTitleShort() + "\n";
        }
        return semester + ":\n" + returnString + "\n";
    }

    public void setSemesterName(String semester) {
        this.semester = semester;
    }
}
