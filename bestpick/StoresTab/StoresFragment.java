package com.bestpickindia.android.bestpick.StoresTab;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestpickindia.android.bestpick.CategoriesTab.CategoryShow;
import com.bestpickindia.android.bestpick.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by HP on 6/21/2016.
 */
public class StoresFragment extends Fragment {
    private ProgressBar loading;
    private ArrayList<String> storenamefinal;
    private ArrayList<String> images;
    private GridView gridview;
    private View mMyView=null;
    private static  int r;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mMyView = inflater.inflate(R.layout.stores_layout,null);
        } else {
            container.removeView(mMyView);
        }

        return mMyView;
        //return inflater.inflate(R.layout.stores_layout,null);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        gridview = (GridView) getActivity().findViewById(R.id.grid_stores);
        loading = (ProgressBar) getActivity().findViewById(R.id.proStores);
        storenamefinal = new ArrayList<String>();
        images = new ArrayList<String>();

    }


    boolean _areLecturesLoaded = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !_areLecturesLoaded) {
            _areLecturesLoaded=true;
            new getData(getActivity()).execute();

        }

    }




    private class getData extends AsyncTask<Void, Void, Void> {
        private Context context;

        public getData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String url = Config1.DATA_URL;
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    showJSON(response);
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
            return null;
        }

        private void showJSON(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray("result");

                int i = result.length();
                Log.i("Result", result.toString());
                for (int j = 0; j < i; j++) {
                    storenamefinal.add(null);
                    images.add(null);
                }
                String temp;
                for (int j = 0; j < i; j++) {
                    JSONObject Data = result.getJSONObject(j);
                    temp = (Data.getString("mainpriority"));
                    storenamefinal.set(Integer.parseInt(temp) - 1, Data.getString("storename"));
                    Log.i("Result Length", storenamefinal.toString());
                    images.set(Integer.parseInt(temp) - 1, Data.getString("imagelinks"));

                }
                if (storenamefinal != null) {
                    final GridAdapterStores gridadapter = new GridAdapterStores(getActivity(), storenamefinal, images);
                    gridview.setAdapter(gridadapter);
                    gridview.setAdapter(gridadapter);
                    gridadapter.notifyDataSetChanged();
                    r++;
                }
                Log.i("Result Length", storenamefinal.toString());
                Toast.makeText(getActivity(), storenamefinal.get(0), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        @Override
        protected void onPostExecute(Void result)

        {
            // super.onPostExecute(null);
            loading.setVisibility(View.GONE);
            gridview.setVisibility(View.VISIBLE);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
           /* Intent i=new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse(gridadapter.getItem(position).getImageUrlString()));
        startActivity(i);}});*/
                    // Toast.makeText(getActivity(), "You Clicked at " + cat[+position], Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), StoreShow.class);
                    i.putExtra("store", storenamefinal.get(position));
                    i.putExtra("image",images.get(position));
                    getActivity().startActivity(i);
                }
            });

        }



    }
}
