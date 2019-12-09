package com.example.collegeapplicationsystem.JSONParsing;

import android.provider.Telephony;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;

public class JSONRetriever {

    private static final String API_KEY_1 = "Jmn22oPvpb8MTlGaI3OoMhF25HtWF7u3XGroS72k";
    private static final String API_KEY_2 = "sKv5sBMASkdn4S1SiSvybvlGkmEb41AweWGoj8MS";
    private static final String API_KEY_3 = "dgUsHXeGByGDW8f0tZNaettzE1VmI7329ru1vLKl";

    private static final String BASE_LINK = "https://api.data.gov/ed/collegescorecard/v1/schools?" +
            "&_fields=id,school.city,school.zip,school.state,school.school_url,school.name,latest.cost.tuition.in_state,latest.cost.tuition.out_of_state,latest.admissions.sat_scores.25th_percentile.critical_reading," +
            "latest.admissions.sat_scores.75th_percentile.critical_reading," +
            "latest.admissions.sat_scores.75th_percentile.math," +
            "latest.admissions.sat_scores.25th_percentile.math&api_key=" + API_KEY_3 +
            "&_per_page=100" +
            "&page=";
    private static final int START_PAGE = 0;
    private static final int TOTAL_PAGES = 71;
    private static final int TIMEOUT_TIME = 30000;

    private int connectionCount = 0;

    private Holder allColleges;

    public JSONRetriever() {
        // allColleges = mapPageToObject(START_PAGE);
    }

    private String getDataFromApi(String link) {
        StringBuilder data = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

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
        //ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectMapper = new ObjectMapper().readerFor(Holder.class);
        Holder holder = null;
        try {
            holder = objectMapper.readValue(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return holder;
    }

    public Holder mapAllPagesToObjects() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                allColleges = mapPageToObject(START_PAGE);
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = START_PAGE + 1; i < TOTAL_PAGES + 1; i++) {
            final int counter = i;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    allColleges.getColleges().addAll(mapPageToObject(counter).getColleges());
                }
            });
            thread.start();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread threadToWaitFor : threadSet) {
            try {
                threadToWaitFor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return allColleges;
    }
}
