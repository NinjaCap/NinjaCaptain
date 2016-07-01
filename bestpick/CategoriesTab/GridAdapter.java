package com.bestpickindia.android.bestpick.CategoriesTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestpickindia.android.bestpick.R;

/**
 * Created by HP on 6/21/2016.
 */
public class GridAdapter extends BaseAdapter{
    private Context context;
    private final String[] cat;
    private final int[] Imageid;
    public GridAdapter(Context c, String[] cat, int[] Imageid) {
        context = c;
        this.Imageid = Imageid;
        this.cat = cat;
    }

    @Override
    public int getCount() {
        return cat.length;
    }

    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_cattab, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(cat[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
