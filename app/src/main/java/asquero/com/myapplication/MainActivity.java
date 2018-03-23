package asquero.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ModelList> listItems;

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

        for (int i = 0; i<10 ; i++){
            ModelList listItem = new ModelList("Dummy text "+ (i+1),0,0,0,0);
            listItems.add(listItem);
        }

        adapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);
    }
}
