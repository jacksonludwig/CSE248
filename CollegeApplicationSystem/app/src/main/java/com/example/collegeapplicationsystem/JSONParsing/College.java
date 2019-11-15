package com.example.collegeapplicationsystem.JSONParsing;

// Required fields:
// ID, Name, Address, City, Zip, State, Web address, In state cost,
// out of state cost, SAT 25th percentile (Reading + math), SAT 75th percentile (Reading + math)
// if some of the above don't exist or are repetitive, don't include them.


import com.fasterxml.jackson.annotation.JsonProperty;

public class College {

    @JsonProperty("latest.cost.tuition.out_of_state")
    private int outOfStateTuition;
    @JsonProperty("latest.cost.tuition.in_state")
    private int inStateTuition;
    @JsonProperty("id")
    private int id;
    @JsonProperty("school.name")
    private String name;
    @JsonProperty("school.city")
    private String city;
    @JsonProperty("school.zip")
    private int zip;
    @JsonProperty("school.state")
    private String state;
    @JsonProperty("school.school_url")
    private String website;
    @JsonProperty("latest.admissions.sat_scores.25th_percentile.critical_reading")
    private int sat25Reading;
    @JsonProperty("latest.admissions.sat_scores.25th_percentile.math")
    private int sat25Math;
    @JsonProperty("latest.admissions.sat_scores.75th_percentile.critical_reading")
    private int sat75Reading;
    @JsonProperty("latest.admissions.sat_scores.75th_percentile.math")
    private int sat75Math;


    public College() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getSat25Reading() {
        return sat25Reading;
    }

    public void setSat25Reading(int sat25Reading) {
        this.sat25Reading = sat25Reading;
    }

    public int getSat25Math() {
        return sat25Math;
    }

    public void setSat25Math(int sat25Math) {
        this.sat25Math = sat25Math;
    }

    public int getSat75Reading() {
        return sat75Reading;
    }

    public void setSat75Reading(int sat75Reading) {
        this.sat75Reading = sat75Reading;
    }

    public int getSat75Math() {
        return sat75Math;
    }

    public void setSat75Math(int sat75Math) {
        this.sat75Math = sat75Math;
    }

    public int getOutOfStateTuition() {
        return outOfStateTuition;
    }

    public void setOutOfStateTuition(int outOfStateTuition) {
        this.outOfStateTuition = outOfStateTuition;
    }

    public int getInStateTuition() {
        return inStateTuition;
    }

    public void setInStateTuition(int inStateTuition) {
        this.inStateTuition = inStateTuition;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", state='" + state + '\'' +
                ", website='" + website + '\'' +
                ", sat25Reading=" + sat25Reading +
                ", sat25Math=" + sat25Math +
                ", sat75Reading=" + sat75Reading +
                ", sat75Math=" + sat75Math +
                '}';
    }
}
