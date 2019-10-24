package com.example.csschedulemaker.courseData;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilities implements Serializable {

    public static ArrayList<Semester> createBaseSemesters(CourseBag courseBag) {
        ArrayList<Semester> semesters = new ArrayList<>();

        Semester fall1 = new Semester("Fall 2019", courseBag);
        semesters.add(fall1);
      //  Semester winter1 = new Semester("Winter 2020", courseBag);
      //  semesters.add(winter1);
        Semester spring1 = new Semester("Spring 2020", courseBag);
        semesters.add(spring1);
       // Semester summer1 = new Semester("Summer 2020", courseBag);
      //  semesters.add(summer1);
        Semester fall2 = new Semester("Fall 2020", courseBag);
        semesters.add(fall2);
      //  Semester winter2 = new Semester("Winter 2021", courseBag);
      //  semesters.add(winter2);
        Semester spring2 = new Semester("Spring 2021", courseBag);
        semesters.add(spring2);

        return semesters;
    }

    public static void saveCourses(CourseBag courses, String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(courses);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CourseBag loadCourses(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return (CourseBag) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CourseBag loadCourses(String fileName, Context context) {
        InputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.getAssets().open(fileName);
            ois = new ObjectInputStream(fis);
            return (CourseBag) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getWordsArray(File file) {
        ArrayList<String> names = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            names = new ArrayList<>();

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                names.add(currentLine);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    public static void buildCoursesFromFile(CourseBag courses, String fileName) {
        File file = new File(fileName);
        String data = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            data = scanner.nextLine();
            Course course = new Course(data);
          //  System.out.println(data);
            courses.insert(course);
        }

    }
}
