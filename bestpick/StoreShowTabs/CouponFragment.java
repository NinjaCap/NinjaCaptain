package com.bestpickindia.android.bestpick.StoreShowTabs;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestpickindia.android.bestpick.AppController;
import com.bestpickindia.android.bestpick.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 6/23/2016.
 */
public class CouponFragment extends Fragment {
    private static final String TAG = CashbackFragment.class.getSimpleName();

    // Movies json url
    private static final String url = "https://tools.vcommission.com/api/coupons.php?apikey=e17acdb4774d758ec9c53d4ec328dba16e32db317043b4d5417d4d230fb94167";
    private ProgressDialog pDialog;
    private List<Coupon> couponList = new ArrayList<Coupon>();
    private ListView listView;
    Toolbar toolbar;
    private CustomListAdapter2 mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        return  inflater.inflate(R.layout.coupons_store,null);
    }
    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) getActivity().findViewById(R.id.list);
        mAdapter = new CustomListAdapter2(getActivity(),couponList);
        listView.setAdapter(mAdapter);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolBar);
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        toolbar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        int checker=0;
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Coupon movie = new Coupon();
                                if(obj.getString("offer_name")=="Limeroad") {
                                    movie.setStore("Limeroad");
                                    movie.setThumbnailUrl("http://www.flipkart.com/apple-touch-icon-144x144.png");
                                    movie.setCoupontitle(obj.getString("coupon_title"));
                                }


                                // adding movie to movies array
                                couponList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);



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
}
