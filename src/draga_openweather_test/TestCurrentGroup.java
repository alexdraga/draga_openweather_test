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


public class TestCurrentGroup {
    private String PATH = "group";
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
    public void testCurrentGroupNoAPIKey() throws IOException, ParseException{
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
    public void testCurrentGroupUnknownKey() throws IOException, ParseException{
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
    public void testCurrentGroupEmptyAPIKey() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyWithoutParams() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyEmptyCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupAPIKeyUnknownCityID() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "?id=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupNoAPIKeyCityID() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyOneCityID() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentGroupAPIKeySameCityIDs() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?id=2172797,2172797&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupAPIKeyCityIDs() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?id=2172797,2172796,2172795&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupTooLongRequestSlash() throws IOException, ParseException{
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
    public void testCurrentGroupUnknownKeySlash() throws IOException, ParseException{
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
    public void testCurrentGroupEmptyAPIKeySlash() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyWithoutParamsSlash() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyEmptyCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupAPIKeyUnknownCityIDSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 404;
        String expectedMessage =
                "Error: Not found city";
        String params = "/?id=JJJJJJJJJJJJ&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupNoAPIKeyCityIDSlash() throws IOException, ParseException{
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
    public void testCurrentGroupAPIKeyOneCityIDSlash() throws IOException, ParseException{
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
        assertTrue("No cod field is present",
                ((JSONObject) responseBody).containsKey("cod"));
        assertEquals("Wrong code in the JSON received.",
                String.valueOf(expectedCod),
                String.valueOf(((JSONObject) responseBody).get("cod")));
        // TODO: write JSON validation
    }

    @Test
    public void testCurrentGroupAPIKeySameCityIDsSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?id=2172797,2172797&appid=" + Helpers.API_KEY;
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
    public void testCurrentGroupAPIKeyCityIDsSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?id=2172797,2172796,2172795&appid=" + Helpers.API_KEY;
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

    public TestCurrentGroup() throws IOException{}
}
