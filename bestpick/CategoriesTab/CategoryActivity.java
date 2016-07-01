package com.bestpickindia.android.bestpick.CategoriesTab;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bestpickindia.android.bestpick.R;
import com.bestpickindia.android.bestpick.TabLayout.TabFragment;

/**
 * Created by HP on 7/1/2016.
 */
public class CategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
   private static String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_main);
        Bundle extras = getIntent().getExtras();
        value = extras.getString("cat");
        setupToolbar();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerViewCategory, new CategoryShow()).commit();
    }
    void setupToolbar(){
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolBar);
        // Set up the activity to use this toolbar. As a side effect this sets the Toolbar's title
        // to the activity's title.
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       /* if (savedInstanceState != null) {
            // Some IDEs such as Android Studio complain about possible NPE without this check.
            assert getSupportActionBar() != null;

            // Restore the Toolbar's title.
            getSupportActionBar().setTitle(
                    savedInstanceState.getCharSequence(value));
        }*/
    }
    public static String getCategoryValue(){
        return value;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }

    }



}
