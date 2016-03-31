package draga_openweather_test;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestCurrent {
	String API_KEY = "8cddc759f83ccdcc0168d33767240c55";
	URL url = new URL("http://api.openweathermap.org/data/2.5/weather");
	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	
	protected void tearDown(){
		connection.disconnect();
	}
	
	public String getResponseBody(HttpURLConnection con) throws IOException {
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
	public void testNoAPIKey() throws MalformedURLException, IOException{
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		String response = getResponseBody(connection);
		int expectedCode = 401;
		String expectedMessage = "";
		assertEquals(String.format(
				"Wrong response code received for %1$s. "
				+ "Expected: %2$s. "
				+ "Received: %3$s", 
				url, expectedCode, code),
				code,
				expectedCode);
	}

	
	public TestCurrent() throws MalformedURLException, IOException{}
}
