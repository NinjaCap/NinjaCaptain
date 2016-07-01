package com.bestpickindia.android.bestpick.StoreShowTabs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bestpickindia.android.bestpick.AppController;
import com.bestpickindia.android.bestpick.R;

import java.util.List;

/**
 * Created by HP on 6/24/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    //private List<Movie> movieItems;
    private List<Movie> cashBack;
    NetworkImageView thumbNail;
    TextView title;
    TextView rating;
    TextView genre;
    private Context context;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Context c, List<Movie> movieItems) {
        this.context=c;
        this.cashBack = movieItems;
    }

    @Override
    public int getCount() {
        return cashBack.size();
    }
    @Override
    public int getViewTypeCount() {

        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public Object getItem(int location) {
        return cashBack.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ListCashback;
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            ListCashback = new View(context);
            ListCashback = inflater.inflate(R.layout.cashback_store_list, null);
             thumbNail = (NetworkImageView)
                    ListCashback.findViewById(R.id.thumbnail);
             title = (TextView) ListCashback.findViewById(R.id.Storename);
             rating = (TextView) ListCashback.findViewById(R.id.Category);
             genre = (TextView)ListCashback.findViewById(R.id.Cashback);
            //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        }
        else {
          ListCashback  = (View) convertView;
        }

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();


        // getting movie data for the row
        Movie m = cashBack.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getStorename());

        // rating
        rating.setText("Category: " + String.valueOf(m.getCategory()));

        // genre
        String genreStr = "Cashback upto ";
        for (String str : m.getCashback()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genreStr+="%";
        genre.setText(genreStr);

        // release year
       // year.setText(String.valueOf(m.getYear()));

        return ListCashback;
    }
}
