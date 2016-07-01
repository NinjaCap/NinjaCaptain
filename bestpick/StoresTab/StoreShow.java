package com.bestpickindia.android.bestpick.StoresTab;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bestpickindia.android.bestpick.R;
import com.bestpickindia.android.bestpick.SessionManager;
import com.bestpickindia.android.bestpick.TabLayout.TabFragment;

/**
 * Created by HP on 6/23/2016.
 */
public class StoreShow  extends AppCompatActivity {
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
    public static String storename;
    public static String imagelink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_show);
        Bundle extras = getIntent().getExtras();
        storename = extras.getString("store");
        imagelink=extras.getString("image");
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*recyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerMainActivity);
        navTitles = getResources().getStringArray(R.array.navDrawerItems);
        navIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);
        recyclerViewAdapter = new RecyclerViewAdapterStores(navTitles,navIcons, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.syncState();*/
        //setupDrawerToggle();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerViewStores, new TabFragmentStores());
        //mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

   /* void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerToggle.setDrawerIndicatorEnabled(false);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }*/
   public static String getStorename() {
       return storename;
   }
    public static String getImagelink() {
        return imagelink;
    }
   @Override
   public boolean onOptionsItemSelected(MenuItem menuItem) {
       switch (menuItem.getItemId()) {
           case android.R.id.home:
               this.finish();
               return true;

       }
       return (super.onOptionsItemSelected(menuItem));

   }

}
