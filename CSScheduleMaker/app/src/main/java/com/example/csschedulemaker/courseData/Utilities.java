package com.example.csschedulemaker.courseData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {

/*    public static ArrayList<String> getWordsArray(File file) {
        HashSet<String> names = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            names = new HashSet<String>();

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                names.add(currentLine);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> nameList = new ArrayList<>(names);
        return nameList;
    }*/

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
}
