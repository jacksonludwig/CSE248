package com.example.collegeapplicationsystem.JSONParsing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class JSONRetriever {

    private static final String API_KEY = "sKv5sBMASkdn4S1SiSvybvlGkmEb41AweWGoj8MS";
    private static final String TAG = JSONRetriever.class.getSimpleName();

    public JSONRetriever() {
    }

    public String getDataFromApi(String link) {
        String data = null;
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setDoOutput(true);

            // give it 15 seconds to respond
            httpURLConnection.setReadTimeout(15 * 1000);
            httpURLConnection.connect();

            data = "";
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    data += scanner.nextLine();
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // need to make College class with desired fields so Jackson can turn JSON in objects
    private Map parseJSONToMap(String data) {
        return null;
    }
}
