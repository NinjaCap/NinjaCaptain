package com.bestpickindia.android.bestpick;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends Activity {
    SessionManager session;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.listMy);
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[8];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.feedback, "HOME");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.account, "PROFILE");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.refer_and_earn, "NOTIFICATIONS");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.feedback, "FAVOURITES");
        drawerItem[4] = new ObjectDrawerItem(R.drawable.refer_and_earn, "INVITE");
        drawerItem[5] = new ObjectDrawerItem(R.drawable.refer_and_earn, "HELP");
        drawerItem[6] = new ObjectDrawerItem(R.drawable.feedback, "FEEDBACK");
        drawerItem[7] = new ObjectDrawerItem(R.drawable.account, "LOGOUT");
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.nav_dra_list_items, drawerItem);
        Log.d("wtf", "listview: " + mDrawerList);
        mDrawerList.setAdapter(adapter);


    }
}