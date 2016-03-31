package draga_openweather_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TestCurrentWeather {
    private String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private HttpURLConnection connection;
    private JSONParser parser = new JSONParser();

    @After
	public void tearDown(){
		connection.disconnect();
	}

    @Test
    public void testWeatherTooLongRequest() throws IOException, ParseException{
        Integer expectedCode = 414;
        String app_id = new String(new char[9000]).replace("\0", "a");
        String params = "?appid=" + app_id;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                code,
                expectedCode);
    }

	@Test
	public void testWeatherNoAPIKey() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        URL url = new URL(API_URL);
        connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
	}

    @Test
    public void testWeatherUnknownKey() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?yyyyyy=yyyy";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherEmptyAPIKey() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?appid=";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
	public void testWeatherAPIKeyWithoutParams() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
	}

    @Test
    public void testWeatherAPIKeyEmptyCity() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?q=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyUnknownCity() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?q=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyCity() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?q=London";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyCity() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyCityUnknownLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London,kkkk&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyEmptyCityLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London,&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyCityLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London,uk&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyUnknownCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=0&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyEmptyCityID() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWrongCityID() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyCityID() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?id=2172797";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?id=2172797&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyEmptyCoordinates() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lon=&lat=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyNoLat() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lon=139&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyNoLon() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWrongLon() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lon=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }
    @Test
    public void testWeatherAPIKeyWrongLat() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lat=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyCoordinates() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?lon=139&lat=35";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyCoordinates() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lon=139&lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyUnknownZIPCode() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?zip=0&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyEmptyZIPCode() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?zip=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWrongZIPCode() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?zip=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyZIPCode() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?zip=94040,us";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyZIPCode() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String params = "?zip=94040,us&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherTooLongRequestSlash() throws IOException, ParseException{
        Integer expectedCode = 414;
        String app_id = new String(new char[9000]).replace("\0", "a");
        String params = "/?appid=" + app_id;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                code,
                expectedCode);
    }

    @Test
    public void testWeatherUnknownKeySlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?yyyyyy=yyyy";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherEmptyAPIKeySlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?appid=";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWithoutParamsSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyEmptyCitySlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?q=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyUnknownCitySlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?q=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyCitySlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?q=London";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyCitySlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyCityUnknownLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London,kkkk&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyEmptyCityLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London,&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyCityLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London,uk&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyUnknownCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=0&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyEmptyCityIDSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWrongCityIDSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?id=2172797";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?id=2172797&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyEmptyCoordinatesSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lon=&lat=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyNoLatSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lon=139&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyNoLonSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWrongLonSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lon=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }
    @Test
    public void testWeatherAPIKeyWrongLatSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lat=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyCoordinatesSlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?lon=139&lat=35";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyCoordinatesSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lon=139&lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

    @Test
    public void testWeatherAPIKeyUnknownZIPCodeSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?zip=0&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyEmptyZIPCodeSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?zip=&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyWrongZIPCodeSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?zip=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherNoAPIKeyZIPCodeSlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?zip=94040,us";
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        assertEquals("Wrong message in the JSON received.",
                ((JSONObject) responseBody).get("message"),
                expectedMessage);
    }

    @Test
    public void testWeatherAPIKeyZIPCodeSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?zip=94040,us&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(((JSONObject) responseBody).get("cod")),
                String.valueOf(expectedCod));
        // TODO: write JSON validation
    }

	public TestCurrentWeather() throws IOException{}
}
