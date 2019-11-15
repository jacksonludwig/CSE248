package com.example.collegeapplicationsystem.JSONParsing;

public class Demo {
    public static void main(String[] args) {
        JSONRetriever jsonRetriever = new JSONRetriever();
        Holder holder = jsonRetriever.mapAllPagesToObjects();
        System.out.println(holder.getColleges().size());
        System.out.println(holder.getColleges().get(5867));
    }
}
