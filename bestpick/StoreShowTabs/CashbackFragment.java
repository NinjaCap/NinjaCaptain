package com.bestpickindia.android.bestpick.StoreShowTabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestpickindia.android.bestpick.AppController;
import com.bestpickindia.android.bestpick.R;
import com.bestpickindia.android.bestpick.StoresTab.StoreShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 6/23/2016.
 */
public class CashbackFragment extends Fragment {
    private static final String TAG = CashbackFragment.class.getSimpleName();
    StoreShow storeShow;
    String storename,imagelink;
    // Movies json url
    private static String url = "http://192.168.1.102/bestpickindia/getCashbackStore.php?storename=";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    Toolbar toolbar;
    private CustomListAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        return  inflater.inflate(R.layout.cashback_store,null);
    }
    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        storeShow = new StoreShow();
        storename=storeShow.getStorename();
        imagelink=storeShow.getImagelink();
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) getActivity().findViewById(R.id.list);
        mAdapter = new CustomListAdapter(getActivity(), movieList);
        listView.setAdapter(mAdapter);
        movieList.clear();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolBar);
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        url +='"'+storename+'"';
        // changing action bar color
        toolbar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                hidePDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");

                    int i = result.length();
                    String length=""+i;
                    Log.i("Result", result.toString());
                    Log.i("Result length", length);
                    for (int j = 0; j < i; j++) {
                        JSONObject obj = result.getJSONObject(j);
                        Movie movie = new Movie();
                        movie.setStorename(obj.getString("storename"));
                        movie.setThumbnailUrl(obj.getString("imagelink"));
                        movie.setCategory(obj.getString("category"));
                        ArrayList<String> genre = new ArrayList<String>();
                        for (int k = 0; k < 1; k++) {
                            genre.add("10");
                        }
                        movie.setCashback(genre);
                        movieList.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.notifyDataSetChanged();
                listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String url = "http://dl.flipkart.com/dl/?affid=rakshithb&affExtParam1=123235465465";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        if(error instanceof NoConnectionError){
                            String Verror="No internet Access,Check your internet connection";
                            Toast.makeText(getActivity(), Verror, Toast.LENGTH_LONG).show();
                        }
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

        // Creating volley request obj
       /* JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setStorename(obj.getString("storename"));
                                movie.setThumbnailUrl(obj.getString("imagelink"));
                                movie.setCategory(((Number) obj.get("category"))
                                        .doubleValue());
                                //movie.setYear(obj.getInt("releaseYear"));

                                // Genre is json array
                                /*JSONArray genreArry = obj.getJSONArray("cashback");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }*/

                                /*ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < 1; j++) {
                                    genre.add("10");
                                }
                                movie.setCashback(genre);

                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);



    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

   /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/


}
