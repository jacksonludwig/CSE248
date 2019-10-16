package com.example.csschedulemaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class getOnlyCSClassesDemo {
    public static void main(String[] args) {
        ArrayList<String> allClasses = new ArrayList<>();
        File file = new File("D:\\CSE248\\CSScheduleMaker\\app\\src\\main\\java\\com\\example\\csschedulemaker\\Courses.txt");
        allClasses = Utilities.getWordsArray(file);
        System.out.println(allClasses.size());

        ArrayList<String> cseClasses = new ArrayList<>();
        ArrayList<String> mathClasses = new ArrayList<>();
        ArrayList<String> scienceClasses = new ArrayList<>();
        ArrayList<String> engClasses = new ArrayList<>();
        ArrayList<String> hisClasses = new ArrayList<>();
        ArrayList<String> gymClasses = new ArrayList<>();
        ArrayList<String> socClasses = new ArrayList<>();
        ArrayList<String> humClasses = new ArrayList<>();
        ArrayList<String> langClasses = new ArrayList<>();

        for (int i = 0; i < allClasses.size(); i++) {
            if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("CSE")) {
                cseClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("MAT")) {
                mathClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("BIO") ||
                    allClasses.get(i).substring(0, 3).equalsIgnoreCase("CHE") ||
                    allClasses.get(i).substring(0, 3).equalsIgnoreCase("PHY")) {
                scienceClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("ENG")) {
                engClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("HIS")) {
                hisClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("PED")) {
                gymClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("ANT") ||
                    allClasses.get(i).substring(0, 3).equalsIgnoreCase("POL")) {
                socClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("HUM")) {
                humClasses.add(allClasses.get(i));
            } else if (allClasses.get(i).substring(0, 3).equalsIgnoreCase("ASL") ||
                    allClasses.get(i).substring(0, 3).equalsIgnoreCase("CHI") ||
                    allClasses.get(i).substring(0, 3).equalsIgnoreCase("FRE") ||
                    allClasses.get(i).substring(0, 3).equalsIgnoreCase("GER")) {
                langClasses.add(allClasses.get(i));
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter("langClasses.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);

        for(int i = 0; i < langClasses.size(); i++) {
            pw.println(langClasses.get(i));
        }
        pw.close();

    }
}
