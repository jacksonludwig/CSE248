package com.example.collegeapplicationsystem.JSONParsing;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        JSONRetriever jsonRetriever = new JSONRetriever();
        Holder holder = jsonRetriever.mapAllPagesToObjects();

        HashMap<String, College> collegeHashMap = jsonRetriever.collegeListToHashMap(holder.getColleges());

        // Utilities.saveColleges(holder.getColleges(), "colleges_list.dat");
        // Utilities.saveColleges(collegeHashMap, "colleges_hash.dat");
    }
}
