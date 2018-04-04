package asquero.com.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ModelList> listItems;

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * URL to query the USGS dataset for earthquake information
     */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //appbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.drawable.app_icon);
        getSupportActionBar().setTitle(R.string.app_name);

        //Initialising RecyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        // Kick off an {@link AsyncTask} to perform the network request
        Log.d(LOG_TAG, "sending asyncTask");
        ContestAsyncTask task = new ContestAsyncTask();
        task.execute();

        /*for (int i = 0; i<10 ; i++){
            if (i==0) {
                ModelList listItem = new ModelList((i + 1), "CodeChef", 0, 0,(R.drawable.logo),"CodeChef" );
                listItems.add(listItem);
            }
            else{
                ModelList listItem = new ModelList((i + 1), "Dummy text " + (i + 1), 0, 0, (R.drawable.default_image),"Dummy text " + (i + 1));
                listItems.add(listItem);
            }
        }
        adapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);*/

    }

    public void setAdapterFunc(){

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

    }

    public void ExtractFromJsonAndupdateUi(String earthquakeJSON) {

        try {

            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("features");

            // If there are results in the features array
            if (featureArray.length() > 0) {

                for (int a = 0; a < 20; a++) {

                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = featureArray.getJSONObject(a);
                    JSONObject properties = firstFeature.getJSONObject("properties");

                    // Extract out the title, time, and tsunami values
                    String title = properties.getString("title");
                    //long time = properties.getLong("time");
                    String time = properties.getString("time");
                    String tsunamiAlert = properties.getString("tsunami");

                    // Create a new {@link ModelList} object
                    Log.d(LOG_TAG, title);
                    ModelList listItem = new ModelList(title, time, tsunamiAlert, "Asquero",0 , title);
                    listItems.add(listItem);
                }
            }
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);


        }
    }

    /**
     * Returns a formatted date and time string for when the earthquake happened.
     */
    /*private String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }*/

    /**
     * Return the display string for whether or not there was a tsunami alert for an earthquake.
     */
    /*private String getTsunamiAlertString(int tsunamiAlert) {
        switch (tsunamiAlert) {
            case 0:
                return getString(R.string.alert_no);
            case 1:
                return getString(R.string.alert_yes);
            default:
                return getString(R.string.alert_not_available);
        }
    }*/

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class ContestAsyncTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(USGS_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            Log.d(LOG_TAG, jsonResponse);
            // Extract relevant fields from the JSON response and create an {@link ModelList} object
            //ModelList earthquake = extractFeatureFromJson(jsonResponse,0);

            // Return the {@link ModelList} object as the result fo the {@link TsunamiAsyncTask}

            if (jsonResponse == null) {
                Log.d(LOG_TAG, "jsonResponse is null");
            } else {
                Log.d(LOG_TAG, "jsonResponse is not null");
            }

            //updateUi(earthquake);
            /*adapter = new MyAdapter(listItems, new MainActivity());
            recyclerView.setAdapter(adapter);*/

            ExtractFromJsonAndupdateUi(jsonResponse);

            return jsonResponse;
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link ContestAsyncTask}).
         */
        @Override
        protected void onPostExecute(String jsonResponse) {
            /*if (jsonResponse == null) {
                Log.d(LOG_TAG, "jsonResponse is null");
                return;
            } else {
                Log.d(LOG_TAG, "jsonResponse is not null");
            }
            //updateUi(earthquake);
            adapter = new MyAdapter(listItems, new MainActivity());
            recyclerView.setAdapter(adapter);
            ExtractFromJsonAndupdateUi(jsonResponse);*/

            setAdapterFunc();

        }
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
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

    /**
     * Return an {@link ModelList} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
        /*private ModelList extractFeatureFromJson(String earthquakeJSON, int a) {
            try {
                // If there are results in the features array
                if (featureArray.length() > 0) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = featureArray.getJSONObject(a);
                    JSONObject properties = firstFeature.getJSONObject("properties");
                    // Extract out the title, time, and tsunami values
                    String title = properties.getString("title");
                    //long time = properties.getLong("time");
                    String time = properties.getString("time");
                    String tsunamiAlert = properties.getString("tsunami");
                    // Create a new {@link ModelList} object
                    return new ModelList(title, time, tsunamiAlert, "Asquero");
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }*/


}