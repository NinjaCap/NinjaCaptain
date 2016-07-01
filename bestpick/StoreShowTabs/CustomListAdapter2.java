package com.bestpickindia.android.bestpick.StoreShowTabs;

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
 * Created by HP on 6/25/2016.
 */
public class CustomListAdapter2 extends BaseAdapter {
    private LayoutInflater inflater;
    //private List<Movie> movieItems;
    private List<Coupon> cashBack;
    NetworkImageView thumbNail;
    TextView store;
    TextView coupontitle;
    private Context context;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter2(Context c, List<Coupon> movieItems) {
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
            ListCashback = inflater.inflate(R.layout.coupons_store_list, null);
            thumbNail = (NetworkImageView)
                    ListCashback.findViewById(R.id.thumbnail);
            store = (TextView) ListCashback.findViewById(R.id.StoreName);
            coupontitle = (TextView) ListCashback.findViewById(R.id.CouponTitle);
            //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        }
        else {
            ListCashback  = (View) convertView;
        }

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();


        // getting movie data for the row
        Coupon m = cashBack.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        store.setText(m.getStore());

        // rating
        coupontitle.setText("" + String.valueOf(m.getCoupontitle()));

        // genr

        // release year
        // year.setText(String.valueOf(m.getYear()));

        return ListCashback;
    }
}
