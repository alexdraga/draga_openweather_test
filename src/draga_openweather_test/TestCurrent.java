package draga_openweather_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TestCurrent {
	String API_KEY = "8cddc759f83ccdcc0168d33767240c55";
	URL url = new URL("http://api.openweathermap.org/data/2.5/weather");
	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	JSONParser parser = new JSONParser();
	
	protected void tearDown(){
		connection.disconnect();
	}

	private String getResponseBody(HttpURLConnection con) throws IOException {
		int code = connection.getResponseCode();
		BufferedReader br;
		if (code > 200 && code < 300) {
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
	   
	@Test
	public void testNoAPIKey() throws IOException, ParseException{
		connection.setRequestMethod("GET");
		connection.connect();
        Integer code = connection.getResponseCode();
		Integer expectedCode = 401;
		String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        Object responseBody = parser.parse(getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCode));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
	}

	
	public TestCurrent() throws IOException{}
}
