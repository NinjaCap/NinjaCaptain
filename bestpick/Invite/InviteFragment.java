package com.bestpickindia.android.bestpick.Invite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestpickindia.android.bestpick.R;

/**
 * Created by HP on 6/21/2016.
 */
public class InviteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        return  inflater.inflate(R.layout.home_layout,null);
    }
}
