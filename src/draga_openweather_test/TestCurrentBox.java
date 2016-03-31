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


public class TestCurrentBox {
    private String API_URL = "http://api.openweathermap.org/data/2.5/box/city";
    private HttpURLConnection connection;
    private JSONParser parser = new JSONParser();

    @After
    public void tearDown(){
        connection.disconnect();
    }

    @Test
    public void testCurrentBoxTooLongRequest() throws IOException, ParseException{
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
    public void testCurrentBoxNoAPIKey() throws IOException, ParseException{
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
    public void testCurrentBoxUnknownKey() throws IOException, ParseException{
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
    public void testCurrentBoxEmptyAPIKey() throws IOException, ParseException{
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
    public void testCurrentBoxAPIKeyWithoutParams() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "";
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
    public void testCurrentBoxAPIKeyEmptyBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "?bbox=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyShortBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "?bbox=12,32&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyWrongBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "?bbox=AAAAAAAA&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&cluster=YYYY&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxEmptyCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&cluster=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&cluster=yes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyNoBBoxCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "";
        String params = "?cluster=yes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&lang=yEEEEEEEes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxEmptyLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&lang=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongCallback() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&callback=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(API_URL + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                code,
                expectedCode);
        // TODO: write JSON validation
    }

    public TestCurrentBox() throws IOException{}
}
