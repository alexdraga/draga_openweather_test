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


public class TestCurrentBox {
    private String PATH = "box/city";
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
    public void testCurrentBoxNoAPIKey() throws IOException, ParseException{
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
    public void testCurrentBoxUnknownKey() throws IOException, ParseException{
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
    public void testCurrentBoxEmptyAPIKey() throws IOException, ParseException{
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
    public void testCurrentBoxAPIKeyWithoutParams() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "";
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
    public void testCurrentBoxAPIKeyEmptyBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "?bbox=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyShortBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "?bbox=12,32&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyWrongBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "?bbox=AAAAAAAA&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBox() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&cluster=YYYY&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxEmptyCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&cluster=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&cluster=yes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyNoBBoxCluster() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "";
        String params = "?cluster=yes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&lang=yEEEEEEEes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxEmptyLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&lang=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxLang() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "?bbox=12,32,15,37&lang=en&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongCallback() throws IOException, ParseException{
        Integer expectedCode = 200;
        String params = "?bbox=12,32,15,37&callback=yEEEEEEEes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxTooLongRequestSlash() throws IOException, ParseException{
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
    public void testCurrentBoxUnknownKeySlash() throws IOException, ParseException{
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
    public void testCurrentBoxEmptyAPIKeySlash() throws IOException, ParseException{
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
    public void testCurrentBoxAPIKeyWithoutParamsSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "";
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
    public void testCurrentBoxAPIKeyEmptyBBoxSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "/?bbox=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyShortBBoxSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "/?bbox=12,32&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyWrongBBoxSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "Bad bbox";
        String params = "/?bbox=AAAAAAAA&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&cluster=YYYY&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxEmptyClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&cluster=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&cluster=yes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyNoBBoxClusterSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 500;
        String expectedMessage =
                "";
        String params = "/?cluster=yes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&lang=yEEEEEEEes&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxEmptyLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&lang=&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxLangSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        Integer expectedCod = 200;
        String params = "/?bbox=12,32,15,37&lang=en&appid=" + Helpers.API_KEY;
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
    public void testCurrentBoxAPIKeyBBoxWrongCallbackSlash() throws IOException, ParseException{
        Integer expectedCode = 200;
        String params = "/?bbox=12,32,15,37&callback=yEEEEEEEes&appid=" + Helpers.API_KEY;
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

    public TestCurrentBox() throws IOException{}
}
