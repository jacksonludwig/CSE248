package com.example.collegeapplicationsystem.JSONParsing;

// Required fields:
// ID, Name, Address, City, Zip, State, Web address, In state cost,
// out of state cost, SAT 25th percentile (Reading + math), SAT 75th percentile (Reading + math)
// if some of the above don't exist or are repetitive, don't include them.
public class College {
    private int id;
    private String name;
    private String city;
    private int zip;
    private String state;
    private String website;
    private int sat25Reading;
    private int sat25Math;
    private int sat75Reading;
    private int sat75Math;

    public College() {

    }

}
