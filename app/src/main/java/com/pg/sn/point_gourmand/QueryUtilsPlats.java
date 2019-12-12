package com.pg.sn.point_gourmand;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by macbookpro on 18/05/2018.
 */

public class QueryUtilsPlats {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtilsPlats() {
    }



    public static List<Plat> fetchPlats(String requestUrl,String name) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Plat> plats = extractPlat(jsonResponse,name);

        // Return the list of {@link Earthquake}s
        return plats;
    }

    public static List<Plat> fetchPlatsDetail(String requestUrl,String name) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Plat> plats = extractPlatDetail(jsonResponse,name);

        // Return the list of {@link Earthquake}s
        return plats;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Plat> extractPlat(String platJson,String nameType) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(platJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        // List<Eearthquakr> earthquakes = new ArrayList<>();

        List<Plat> pla = new ArrayList<>();

        // Try to parse the JSON RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(platJson);
            Iterator<String> iter = baseJsonResponse.keys();


            while (iter.hasNext()) {
                String key = iter.next();

                JSONObject value = baseJsonResponse.getJSONObject(key);
                String name = value.getString("nom");
                String image = value.getString("image");
                String type = value.getString("type");
                int prix = value.getInt("prix");
                if (type.equals(nameType) ) {
                    pla.add((new Plat(name,image,type,prix)));
                }

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        //return earthquakes;

        return pla ;
    }

    private static List<Plat> extractPlatDetail(String platJson,String nameType) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(platJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        // List<Eearthquakr> earthquakes = new ArrayList<>();

        List<Plat> pla = new ArrayList<>();

        // Try to parse the JSON RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(platJson);
            Iterator<String> iter = baseJsonResponse.keys();


            while (iter.hasNext()) {
                String key = iter.next();

                JSONObject value = baseJsonResponse.getJSONObject(key);
                String name = value.getString("nom");
                String image = value.getString("image");
                String type = value.getString("type");
                int prix = value.getInt("prix");
                if (name.equals(nameType)) {
                    pla.add((new Plat(name,image,type,prix)));
                }

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        //return earthquakes;

        return pla ;
    }

}
