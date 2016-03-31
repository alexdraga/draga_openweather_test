package draga_openweather_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

class Helpers {
    static String API_KEY = "8cddc759f83ccdcc0168d33767240c55";

    static String getResponseBody(HttpURLConnection con) throws IOException {
        int code = con.getResponseCode();
        BufferedReader br;
        if (code >= 200 && code < 300) {
            br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        } else {
            br = new BufferedReader(new InputStreamReader((con.getErrorStream())));
        }
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        return sb.toString();
    }
}
