package com.bestpickindia.android.bestpick;
import com.bestpickindia.android.bestpick.TabLayout.PagerAdapter;
import com.bestpickindia.android.bestpick.TabLayout.TabFragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SessionManager session;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    String navTitles[];
    TypedArray navIcons;
    RecyclerView.Adapter recyclerViewAdapter;
    ActionBarDrawerToggle drawerToggle;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    /*ViewPager pager;
    ViewPagerAdapter adapter;
    CharSequence Titles[]={"Home","Events"};
    int Numboftabs =2;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Let's first set up toolbar
        setupToolbar();
        /*TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));
        tabLayout.addTab(tabLayout.newTab().setText("Stores"));
        tabLayout.addTab(tabLayout.newTab().setText("Invite"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        //Initialize Views
        recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerMainActivity);

        //Setup Titles and Icons of Navigation Drawer
        navTitles = getResources().getStringArray(R.array.navDrawerItems);
        navIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);


        /**
         *Here , pass the titles and icons array to the adapter .
         *Additionally , pass the context of 'this' activity .
         *So that , later we can use the fragmentManager of this activity to add/replace fragments.
         */

        recyclerViewAdapter = new RecyclerViewAdapter(navTitles,navIcons,this);
        recyclerView.setAdapter(recyclerViewAdapter);

        /**
         *It is must to set a Layout Manager For Recycler View
         *As per docs ,
         *RecyclerView allows client code to provide custom layout arrangements for child views.
         *These arrangements are controlled by the RecyclerView.LayoutManager.
         *A LayoutManager must be provided for RecyclerView to function.
         */

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Finally setup ActionBarDrawerToggle
        setupDrawerToggle();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        //Add the Very First i.e Squad Fragment to the Container
       /* Fragment tabFragment = new TabFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerView,tabFragment,null);
        fragmentTransaction.commit();*/

    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = this.getSupportFragmentManager();



        if (fragmentManager.getBackStackEntryCount() == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

    }
}


}