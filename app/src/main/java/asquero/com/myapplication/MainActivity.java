package asquero.com.myapplication;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter eventListAdapter;

    private List<EventList> listEvent;

    private String[] sub_items;
    private boolean[] checkedItems;
    private ArrayList<Integer> mSelectedItems = new ArrayList<>();
    public String[] items = {"","",""};

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

        mDrawer = (DrawerLayout)findViewById(R.id.navDrawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);

        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listEvent = new ArrayList<>();

        sub_items = getResources().getStringArray(R.array.subscription);
        checkedItems = new boolean[sub_items.length];

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean previouslyStarted = prefs.getBoolean("previously started", false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("previously started", Boolean.TRUE);
            edit.apply();
            showSubscription();
        }

        for (int i = 0; i<= 2 ; i++){
            if (i==0) {
                EventList listItem = new EventList("Live", (R.drawable.live),items);
                listEvent.add(listItem);
            }
            if (i==1) {
                EventList listItem = new EventList("Upcoming", (R.drawable.upcoming),items);
                listEvent.add(listItem);
            }
            if (i==2) {
                EventList listItem = new EventList("Ended", (R.drawable.ended),items);
                listEvent.add(listItem);
            }
        }

        eventListAdapter = new EventListAdapter(listEvent,this);
        recyclerView.setAdapter(eventListAdapter);

    }

    private void showSubscription() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Please select at least one subscription");
        mBuilder.setMultiChoiceItems(sub_items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                if (isChecked){
                    if (!mSelectedItems.contains(position)){
                        mSelectedItems.add(position);
                    }else mSelectedItems.remove(position);
                }
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("Add Subscription", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < mSelectedItems.size();i++){
                    if (mSelectedItems.size() == 0){
                        for (int j = 0; j < sub_items.length;j++){
                            items[j] = sub_items[j];
                        }
                    }
                    else
                    items[i] = sub_items[mSelectedItems.get(i)];
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}