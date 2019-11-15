package com.example.collegeapplicationsystem.JSONParsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) throws JsonProcessingException {
        String url = "https://api.data.gov/ed/collegescorecard/v1/schools?&_fields=id,school.city,school.zip,school.state,school.school_url,school.name,latest.cost.tuition.in_state,latest.cost.tuition.out_of_state,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.math,latest.admissions.sat_scores.25th_percentile.math&api_key=sKv5sBMASkdn4S1SiSvybvlGkmEb41AweWGoj8MS&_per_page=100&page=20";
        JSONRetriever jsonRetriever = new JSONRetriever();
        String data = jsonRetriever.getDataFromApi(url);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {});

        ObjectMapper objectMapper1 = new ObjectMapper();
        //College[] colleges = objectMapper.readValue(jsonMap.get("results").toString(), College[].class);
        //List<College> list = Arrays.asList(objectMapper1.readValue(jsonMap.get("results").toString(), College[].class));
        System.out.println(jsonMap.get("results"));
        //System.out.println(list);
    }
}
