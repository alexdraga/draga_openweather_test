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


public class TestCurrentFind {
    private String PATH = "find";
    private HttpURLConnection connection;
    private JSONParser parser = new JSONParser();

    @After
    public void tearDown(){
        connection.disconnect();
    }

    @Test
    public void testCurrentFindTooLongRequest() throws IOException, ParseException{
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
    public void testCurrentFindNoAPIKey() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindUnknownKey() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindEmptyAPIKey() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyWithoutParams() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }
    @Test
    public void testCurrentFindAPIKeyNoLat() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "?lon=37&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyEmptyLat() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "?lat=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyEmptyNoLon() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "?lat=55&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyEmptyLon() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "?lon=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyWrongLat() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String expectedMessage =
                "accurate";
        String params = "?lat=AAA&lon=37&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
        assertTrue("Actually, this is a bug, but I can not come with required behaviour, so let's leave it.", false);
    }

    @Test
    public void testCurrentFindAPIKeyWrongLon() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String expectedMessage =
                "accurate";
        String params = "?lat=55&lon=AAAAA&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
        assertTrue("Actually, this is a bug, but I can not come with required behaviour, so let's leave it.", false);
    }

    @Test
    public void testCurrentFindAPIKeyCoordinates() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&lang=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesEmptyLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&lang=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&lang=en&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&cluster=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesEmptyCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&cluster=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&cluster=yes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongCNT() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&cnt=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesEmptyCNT() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&cnt=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesCNT() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?lat=55&lon=37&cnt=3&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongCallback() throws IOException, ParseException{
        Integer expectedCode = 200;
        String params = "?lat=55&lon=37&callback=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindTooLongRequestSlash() throws IOException, ParseException{
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
    public void testCurrentFindUnknownKeySlash() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindEmptyAPIKeySlash() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyWithoutParamsSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }
    @Test
    public void testCurrentFindAPIKeyNoLatSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "/?lon=37&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyEmptyLatSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "/?lat=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyEmptyNoLonSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "/?lat=55&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyEmptyLonSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "bad request";
        String params = "/?lon=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
    }

    @Test
    public void testCurrentFindAPIKeyWrongLatSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String expectedMessage =
                "accurate";
        String params = "/?lat=AAA&lon=37&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
        assertTrue("Actually, this is a bug, but I can not come with required behaviour, so let's leave it.", false);
    }

    @Test
    public void testCurrentFindAPIKeyWrongLonSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String expectedMessage =
                "accurate";
        String params = "/?lat=55&lon=AAAAA&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertTrue("No message field is present",
                ((JSONObject) responseBody).containsKey("message"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        assertEquals("Wrong message in the JSON received.",
                expectedMessage,
                ((JSONObject) responseBody).get("message"));
        assertTrue("Actually, this is a bug, but I can not come with required behaviour, so let's leave it.", false);
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&lang=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesEmptyLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&lang=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&lang=en&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&cluster=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesEmptyClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&cluster=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&cluster=yes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongCNTSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&cnt=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesEmptyCNTSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&cnt=&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesCNTSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?lat=55&lon=37&cnt=3&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        Object responseBody = parser.parse(Helpers.getResponseBody(connection));
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentFindAPIKeyCoordinatesWrongCallbackSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        String params = "/?lat=55&lon=37&callback=yEEEEEEEes&appid=" + Helpers.API_KEY;
        URL url = new URL(Helpers.API_URL + PATH + params);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Integer code = connection.getResponseCode();
        assertEquals("Wrong response code received",
                expectedCode,
                code);
        // TODO: write JSON validation
    }

    public TestCurrentFind() throws IOException{}
}
