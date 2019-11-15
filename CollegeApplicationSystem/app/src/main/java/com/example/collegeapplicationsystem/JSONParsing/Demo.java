package com.example.collegeapplicationsystem.JSONParsing;

public class Demo {
    public static void main(String[] args) {
        JSONRetriever jsonRetriever = new JSONRetriever();
        Holder holder = jsonRetriever.mapAllPagesToObjects();
        System.out.println(holder.getColleges().size());
       // Holder holder = jsonRetriever.mapPageToObject(20);
       // System.out.println(holder.getColleges());

      //  Utilities.saveColleges(holder.getColleges(), "colleges.dat");
    }
}
