package com.bestpickindia.android.bestpick.TabLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestpickindia.android.bestpick.CategoriesTab.CategoriesFragment;
import com.bestpickindia.android.bestpick.HomeTab.HomeFragment;
import com.bestpickindia.android.bestpick.Invite.InviteFragment;
import com.bestpickindia.android.bestpick.R;
import com.bestpickindia.android.bestpick.StoresTab.StoresFragment;

import java.util.ArrayList;

/**
 * Created by HP on 6/23/2016.
 */
public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tabs_layout_main,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        initFragments();
        viewPager.setOffscreenPageLimit(3);

        /**
         *Set an Apater for the View Pager
         */
        // viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }
    private void initFragments() {

        ArrayList< Fragment > fragmentArrayList = new ArrayList < > ();

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new CategoriesFragment());
        fragmentArrayList.add(new StoresFragment());
        fragmentArrayList.add(new InviteFragment());

        MyAdapter viewPagerAdapter = new MyAdapter(getFragmentManager(), fragmentArrayList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MyAdapter extends FragmentPagerAdapter {
        private ArrayList < Fragment > fragments;
        public MyAdapter(FragmentManager fm ,ArrayList < Fragment > fragments) {
            super(fm);
            this.fragments = fragments;
        }



        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Home";
                case 1 :
                    return "Categories";
                case 2 :
                    return "Stores";
                case 3:
                    return "Invite";
            }
            return null;
        }
    }
}
