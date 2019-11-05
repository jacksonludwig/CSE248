package p1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://api.data.gov/ed/collegescorecard/v1/schools?school.name=boston%20college&_fields=latest.student.size,id,school.name&api_key=sKv5sBMASkdn4S1SiSvybvlGkmEb41AweWGoj8MS&_per_page=100");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        String inline = "";
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            System.out.println(inline);
            scanner.close();
        }
        // from Jackson
        ObjectMapper objectMapper = new ObjectMapper();


    }
}
