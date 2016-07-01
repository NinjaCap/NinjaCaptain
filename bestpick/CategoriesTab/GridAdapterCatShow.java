package com.bestpickindia.android.bestpick.CategoriesTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bestpickindia.android.bestpick.R;
import com.bestpickindia.android.bestpick.StoresTab.CustomVolleyRequest;

import java.util.ArrayList;

/**
 * Created by HP on 6/21/2016.
 */
public class GridAdapterCatShow extends BaseAdapter{
    private ImageLoader imageLoader;
    private Context context;
    private ArrayList<String> cat_name=new ArrayList<String>();
    private  ArrayList<String> cat_com=new ArrayList<String>();
    private  ArrayList<String> Imageid=new ArrayList<String>();
    public GridAdapterCatShow(Context c, ArrayList<String> cat_name,ArrayList<String> cat_com, ArrayList<String> Imageid) {
        context = c;
        this.Imageid = Imageid;
        this.cat_name = cat_name;
        this.cat_com=cat_com;
    }

    @Override
    public int getCount() {
        return this.cat_com.size();
    }

    public Object getItem(int position) {
        return null;
    }

    @Override
    public boolean isEnabled(int i){
        return true;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        if (convertView == null) {
            grid = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = inflater.inflate(R.layout.grid_catshow,parent,false);

        } else {
            grid = (View) convertView;
        }
        NetworkImageView networkImageView = (NetworkImageView) grid.findViewById(R.id.thumbnail);
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(Imageid.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        networkImageView.setImageUrl(Imageid.get(position),imageLoader);
        TextView textViewCom = (TextView) grid.findViewById(R.id.grid_text_com);
        ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
        TextView textViewName = (TextView) grid.findViewById(R.id.grid_text_name);

        textViewName.setText(cat_name.get(position));
        textViewCom.setText("Upto " + cat_com.get(position) + "%");

        //imageView.setImageResource(R.drawable.about);
        return grid;
    }

}
