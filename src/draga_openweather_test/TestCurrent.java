package draga_openweather_test;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import static org.junit.Assert.*;


public class TestCurrent {
	URL url = new URL("http://api.openweathermap.org/data/2.5/weather");
	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	
	protected void tearDown(){
		connection.disconnect();
	}
	   
	@Test
	public void testResponseCode() throws MalformedURLException, IOException{
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		int expectedCode = 200;
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
