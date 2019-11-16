package com.example.collegeapplicationsystem.JSONParsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class JSONRetriever {

    private static final String BASE_LINK = "https://api.data.gov/ed/collegescorecard/v1/schools?" +
            "&_fields=id,school.city,school.zip,school.state,school.school_url,school.name,latest.cost.tuition.in_state,latest.cost.tuition.out_of_state,latest.admissions.sat_scores.25th_percentile.critical_reading," +
            "latest.admissions.sat_scores.75th_percentile.critical_reading," +
            "latest.admissions.sat_scores.75th_percentile.math," +
            "latest.admissions.sat_scores.25th_percentile.math&api_key=sKv5sBMASkdn4S1SiSvybvlGkmEb41AweWGoj8MS&_per_page=100" +
            "&page=";
    private static final int START_PAGE = 0;
    private static final int TOTAL_PAGES = 71;
    private static final int TIMEOUT_TIME = 30000;

    private static int connectionCount = 0;

    public JSONRetriever() {
    }

    private String getDataFromApi(String link) {
        StringBuilder data = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setReadTimeout(TIMEOUT_TIME);
            httpURLConnection.connect();
            System.out.println("API Call #" + ++connectionCount);

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    data.append(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public Holder mapPageToObject(int page) {
        String data = getDataFromApi(BASE_LINK + page);
        ObjectMapper objectMapper = new ObjectMapper();
        Holder holder = null;
        try {
            holder = objectMapper.readValue(data, Holder.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return holder;
    }

    public Holder mapAllPagesToObjects() {
        Holder allColleges = mapPageToObject(START_PAGE);
        for (int i = START_PAGE + 1; i < TOTAL_PAGES + 1; i++) {
            allColleges.getColleges().addAll(mapPageToObject(i).getColleges());
        }
        return allColleges;
    }

    public HashMap<String, College> collegeListToHashMap(List<College> colleges) {
        HashMap<String, College> collegeHashMap = new HashMap<>();
        for (College college : colleges) {
            collegeHashMap.put(String.valueOf(college.getId()),college);
        }
        return collegeHashMap;
    }

}
