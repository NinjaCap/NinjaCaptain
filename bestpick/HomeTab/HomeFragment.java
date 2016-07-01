package com.bestpickindia.android.bestpick.HomeTab;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestpickindia.android.bestpick.R;

/**
 * Created by HP on 6/21/2016.
 */
public class HomeFragment extends Fragment {
    static TextView mDotsText[];
    private int mDotsCount;
    private LinearLayout mDotsLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        return  inflater.inflate(R.layout.home_layout,null);
    }
    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Gallery gallery = (Gallery)getView().findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(getActivity()));


        mDotsLayout = (LinearLayout)getView().findViewById(R.id.image_count);
        //here we count the number of images we have to know how many dots we need
        mDotsCount = gallery.getAdapter().getCount();

        //here we create the dots
        //as you can see the dots are nothing but "."  of large size
        mDotsText = new TextView[mDotsCount];

        //here we set the dots
        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(getActivity());
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(45);
            mDotsText[i].setTypeface(null, Typeface.BOLD);
            mDotsText[i].setTextColor(android.graphics.Color.GRAY);
            mDotsLayout.addView(mDotsText[i]);
        }


        //when we scroll the images we have to set the dot that corresponds to the image to White and the others
        //will be Gray
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int pos, long l) {

                for (int i = 0; i < mDotsCount; i++) {
                    HomeFragment.mDotsText[i]
                            .setTextColor(Color.GRAY);
                }

                HomeFragment.mDotsText[pos]
                        .setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });
    }
}
