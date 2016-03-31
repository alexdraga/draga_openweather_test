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


public class TestCurrentGroup {
    private String API_URL = "http://api.openweathermap.org/data/2.5/group";
    private HttpURLConnection connection;
    private JSONParser parser = new JSONParser();

    @After
    public void tearDown(){
        connection.disconnect();
    }

    @Test
    public void testCurrentGroupTooLongRequest() throws IOException, ParseException{
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
    public void testCurrentGroupNoAPIKey() throws IOException, ParseException{
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
    public void testCurrentGroupUnknownKey() throws IOException, ParseException{
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
    public void testCurrentGroupEmptyAPIKey() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyWithoutParams() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyEmptyCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupAPIKeyUnknownCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupNoAPIKeyCityID() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyOneCityID() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeySameCityIDs() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?id=2172797,2172797&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupAPIKeyCityIDs() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?id=2172797,2172796,2172795&appid=" + Helpers.API_KEY;
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

    public TestCurrentGroup() throws IOException{}
}
