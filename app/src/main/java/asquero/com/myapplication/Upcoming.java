package asquero.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Upcoming extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter upcomingListAdapter;

    private List<UpcomingList> listUpcoming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        getSupportActionBar().setTitle("Upcoming");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listUpcoming = new ArrayList<>();

        for (int i = 0; i< 20 ; i++){
            UpcomingList upcomingLists = new UpcomingList("DummyCode"+i,"DummyName"+i,"0","0","DummyName"+i);
            listUpcoming.add(upcomingLists);
        }

        upcomingListAdapter = new UpcomingListAdapter(listUpcoming,Upcoming.this);
        recyclerView.setAdapter(upcomingListAdapter);
    }
}
