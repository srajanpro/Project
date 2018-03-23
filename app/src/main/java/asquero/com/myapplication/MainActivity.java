package asquero.com.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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

    private ViewPager viewPager;
    private SectionPagerAdapter mSectionPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //appbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setIcon(R.drawable.app_icon);
        getSupportActionBar().setTitle(R.string.app_name);

        //tabs
        viewPager = (ViewPager)findViewById(R.id.TabPager);
        mSectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionPagerAdapter);

        mTabLayout = (TabLayout)findViewById(R.id.mainTabs);

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
