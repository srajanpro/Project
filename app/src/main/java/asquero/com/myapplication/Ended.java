package asquero.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Ended extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter endedListAdapter;

    private List<EndedList> listEnded;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ended);

        getSupportActionBar().setTitle("Ended");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listEnded = new ArrayList<>();

        for (int i = 0; i< 20 ; i++){
            EndedList EndedLists = new EndedList("DummyCode"+i,"DummyName"+i,"0","0","DummyName"+i);
            listEnded.add(EndedLists);
        }

        endedListAdapter = new EndedListAdapter(listEnded,Ended.this);
        recyclerView.setAdapter(endedListAdapter);
    }
}