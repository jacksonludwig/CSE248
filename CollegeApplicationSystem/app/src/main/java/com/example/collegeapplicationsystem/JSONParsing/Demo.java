package com.example.collegeapplicationsystem.JSONParsing;

public class Demo {
    public static void main(String[] args) {
        JSONRetriever jsonRetriever = new JSONRetriever();
        String myURL ="https://api.data.gov/ed/collegescorecard/v1/schools?school.name=boston%20college&_fields=latest.student.size,id,school.name&api_key=sKv5sBMASkdn4S1SiSvybvlGkmEb41AweWGoj8MS&_per_page=100";
        String data = jsonRetriever.getDataFromApi(myURL);
        System.out.println(data);
    }
}
