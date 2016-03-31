package draga_openweather_test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestForecast {
    private String PATH = "forecast";
    private HttpURLConnection connection;
    private JSONParser parser = new JSONParser();

    @After
    public void tearDown(){
        connection.disconnect();
    }

    @Test
    public void testForecastTooLongRequest() throws IOException, ParseException{
        Integer expectedCode = 414;
        String app_id = new String(new char[9000]).replace("\0", "a");
        String params = "?appid=" + app_id;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                expectedCode,
                code);
    }

    @Test
    public void testForecastNoAPIKey() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        URL url = new URL(Helpers.API_URL + PATH);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastUnknownKey() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?yyyyyy=yyyy";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastEmptyAPIKey() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?appid=";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWithoutParams() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyEmptyCity() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?q=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyUnknownCity() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?q=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyCity() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?q=London";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyCity() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyCityUnknownLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London,kkkk&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyEmptyCityLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London,&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyCityLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?q=London,uk&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyUnknownCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=0&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyEmptyCityID() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWrongCityID() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyCityID() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?id=2172797";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?id=2172797&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyEmptyCoordinates() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lon=&lat=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyNoLat() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lon=139&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyNoLon() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWrongLon() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lon=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }
    @Test
    public void testForecastAPIKeyWrongLat() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?lat=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyCoordinates() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?lon=139&lat=35";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyCoordinates() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lon=139&lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyUnknownZIPCode() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?zip=0&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyEmptyZIPCode() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?zip=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWrongZIPCode() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?zip=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyZIPCode() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "?zip=94040,us";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyZIPCode() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String params = "?zip=94040,us&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastTooLongRequestSlash() throws IOException, ParseException{
        Integer expectedCode = 414;
        String app_id = new String(new char[9000]).replace("\0", "a");
        String params = "/?appid=" + app_id;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                expectedCode,
                code);
    }

    @Test
    public void testForecastUnknownKeySlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?yyyyyy=yyyy";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastEmptyAPIKeySlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?appid=";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWithoutParamsSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyEmptyCitySlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?q=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyUnknownCitySlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?q=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyCitySlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?q=London";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyCitySlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyCityUnknownLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London,kkkk&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyEmptyCityLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London,&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyCityLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?q=London,uk&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyUnknownCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=0&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyEmptyCityIDSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWrongCityIDSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=aaaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?id=2172797";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?id=2172797&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testForecastAPIKeyEmptyCoordinatesSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lon=&lat=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyNoLatSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lon=139&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyNoLonSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyWrongLonSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lon=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }
    @Test
    public void testForecastAPIKeyWrongLatSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?lat=aaaa&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastNoAPIKeyCoordinatesSlash() throws IOException, ParseException{
        Integer expectedCode = 401;
        Integer expectedCod = 401;
        String expectedMessage =
                "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";
        String params = "/?lon=139&lat=35";
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testForecastAPIKeyCoordinatesSlash() throws IOException, ParseException {
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lon=139&lat=35&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present in the JSON received.",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    public TestForecast() throws IOException{}
}
