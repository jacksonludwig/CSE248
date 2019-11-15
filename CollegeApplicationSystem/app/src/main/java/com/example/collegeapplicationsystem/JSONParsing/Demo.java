package com.example.collegeapplicationsystem.JSONParsing;

public class Demo {
    public static void main(String[] args) {
        JSONRetriever jsonRetriever = new JSONRetriever();
       // Holder holder = jsonRetriever.mapToObject(71);
       // System.out.println(holder.getColleges());
        Holder holder = jsonRetriever.mapAllObjects();
        System.out.println(holder.getColleges().size());
    }
}
