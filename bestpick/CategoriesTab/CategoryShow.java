package com.bestpickindia.android.bestpick.CategoriesTab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestpickindia.android.bestpick.MainActivity;
import com.bestpickindia.android.bestpick.R;
import com.bestpickindia.android.bestpick.SplashActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by HP on 6/21/2016.
 */
public class CategoryShow extends Fragment{
    private TextView textViewResult;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    View mMyView;
    private ProgressDialog loading;

    private Toolbar toolbar;
    private String value;
   private final ArrayList<String>  cat_name=new ArrayList<String>();
    private final ArrayList<String>  cat_com=new ArrayList<String>();
    private ArrayList<String> images;
    private ArrayList<String> affiliateLink= new ArrayList<String>();
   /* private void setupToolbar(final Bundle savedInstanceState) {
        toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.toolbar);
        // Set up the activity to use this toolbar. As a side effect this sets the Toolbar's title
        // to the activity's title.
        getActivity().setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState != null) {
            // Some IDEs such as Android Studio complain about possible NPE without this check.
            assert getSupportActionBar() != null;

            // Restore the Toolbar's title.
            getSupportActionBar().setTitle(
                    savedInstanceState.getCharSequence(value));
        }
    }
*/
   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
       if (savedInstanceState == null) {
           mMyView = inflater.inflate(R.layout.categories_show_layout,null);
       } else {
           container.removeView(mMyView);

       }
       return mMyView;

   }
    boolean _areStoresLoaded = false;


   @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
       super.onViewCreated(view, savedInstanceState);
       images = new ArrayList<String>();
       getData();

    }


    @Override
     public void onResume() {
        super.onResume();

    }

    private void getData() {
        CategoryActivity categoryActivity = new CategoryActivity();
        String value=categoryActivity.getCategoryValue();
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        int x=1;
        String url = Config.DATA_URL+'"'+value+'"';
        //Toast.makeText(this,url, Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            //Toast.makeText(category_show.this,result.toString(),Toast.LENGTH_LONG).show();
            int i=result.length();
            for(int j=0;j<i;j++)
            {
                JSONObject Data = result.getJSONObject(j);
                cat_name.add(Data.getString(Config.KEY_NAME));
                cat_com.add(Data.getString(Config.KEY_COM));
                images.add(Data.getString(Config.KEY_IMA));
                affiliateLink.add(Data.getString(Config.KEY_AFF));
            }
            GridView gridview=(GridView) getActivity().findViewById(R.id.grid_catshow);
            final GridAdapterCatShow gridadapter= new GridAdapterCatShow(getActivity(),cat_name,cat_com,images);
            gridview.setAdapter(gridadapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /* public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            /* Intent i=new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(gridadapter.getItem(position).getImageUrlString()));
         startActivity(i);}});*/
                // Toast.makeText(getActivity(), "You Clicked at " + cat[+position], Toast.LENGTH_SHORT).show();
                    /*mFragmentManager = getFragmentManager();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.containerViewCategory, new CategoryStore());
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                    cat_name.clear();
                    cat_com.clear();
                    images.clear();

                }*/
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = affiliateLink.get(position);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });



            System.out.println(cat_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}
