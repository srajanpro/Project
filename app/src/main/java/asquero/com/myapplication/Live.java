package asquero.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Live extends AppCompatActivity {

    private int arrSize;
    private String[] subs;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter liveListAdapter;

    private List<LiveList> listLive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        getSupportActionBar().setTitle("Live");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        subs = intent.getStringArrayExtra("subs");

        arrSize = subs.length;

        listLive = new ArrayList<>();

        for (int i = 0; i< arrSize ; i++){
            LiveList liveLists = new LiveList("DummyCode"+i,subs[i],"0","0","DummyName"+i);
            listLive.add(liveLists);
        }

        liveListAdapter = new LiveListAdapter(listLive,Live.this);
        recyclerView.setAdapter(liveListAdapter);
    }
}
