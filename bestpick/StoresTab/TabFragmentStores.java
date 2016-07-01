package com.bestpickindia.android.bestpick.StoresTab;

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
import com.bestpickindia.android.bestpick.StoreShowTabs.CashbackFragment;
import com.bestpickindia.android.bestpick.StoreShowTabs.CouponFragment;
import com.bestpickindia.android.bestpick.StoreShowTabs.Dummy;

import java.util.ArrayList;

/**
 * Created by HP on 6/23/2016.
 */
public class TabFragmentStores extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    String storename;
    String imagelink;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout_store,null);
        StoreShow storeShow=new StoreShow();
        storename=storeShow.getStorename();
        imagelink=storeShow.getImagelink();
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        initFragments();
        viewPager.setOffscreenPageLimit(1);

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

        fragmentArrayList.add(new CashbackFragment());
        fragmentArrayList.add(new Dummy());

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
                    return "Cashback";
                case 1 :
                    return "Coupons & Deals";

            }
            return null;
        }
    }
}
