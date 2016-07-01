package com.bestpickindia.android.bestpick.StoresTab;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bestpickindia.android.bestpick.R;


import java.util.ArrayList;

/**
 * Created by HP on 6/23/2016.
 */
public class GridAdapterStores extends BaseAdapter{
    //Imageloader to load images
    private ImageLoader imageLoader;
    private Context context;
    private ArrayList<String> storename = new ArrayList<String>();

    private ArrayList<String> images=new ArrayList<String>();

    public GridAdapterStores(Context c, ArrayList<String> storename, ArrayList<String> imagelinks) {
        context = c;
        this.images=imagelinks;
        this.storename = storename;
    }

    @Override
    public int getCount() {
        return storename.size();
    }

    public Object getItem(int position) {
        return storename.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        /*View grid;
        if (convertView == null) {
            grid = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.grid_stores, parent, false);
        } else {
            grid = (View) convertView;
        }*/
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //NetworkImageView
        NetworkImageView networkImageView = new NetworkImageView(context);

        //Initializing ImageLoader
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //Setting the image url to load
        networkImageView.setImageUrl(images.get(position),imageLoader);

        //Creating a textview to show the title
        TextView textView = new TextView(context);
        textView.setText(storename.get(position));

        //Scaling the imageview
        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setLayoutParams(new GridView.LayoutParams(200, 200));

        //Adding views to the layout
        linearLayout.addView(networkImageView);
        linearLayout.addView(textView);

        //Returnint the layout
        return linearLayout;


       /* LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        NetworkImageView networkImageView = new NetworkImageView(context);
        //Initializing ImageLoader
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        //Setting the image url to load
        networkImageView.setImageUrl(images.get(position), imageLoader);
        //Creating a textview to show the title
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.WHITE);
        textView.setText(storename.get(position));
        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        networkImageView.setBackgroundColor(Color.WHITE);

        //Adding views to the layout

        linearLayout.addView(networkImageView);
        linearLayout.addView(textView);

        //Returnint the layout
        return linearLayout;*/
//  Integer x=imagelinks.get(position);
//  imageViewStoreImage.setImageResource(x);
        //       return grid;
    }
}
