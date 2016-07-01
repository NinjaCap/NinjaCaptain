package com.bestpickindia.android.bestpick.TabLayout;

import android.support.v4.app.FragmentStatePagerAdapter;

import com.bestpickindia.android.bestpick.CategoriesTab.CategoriesFragment;
import com.bestpickindia.android.bestpick.HomeTab.HomeFragment;
import com.bestpickindia.android.bestpick.Invite.InviteFragment;
import com.bestpickindia.android.bestpick.StoresTab.StoresFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HP on 6/23/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    HomeFragment tab1 = new HomeFragment();
                    return tab1;
                case 1:
                    CategoriesFragment tab2 = new CategoriesFragment();
                    return tab2;
                case 2:
                    StoresFragment tab3 = new StoresFragment();
                    return tab3;
                case 3:
                    InviteFragment tab4 = new InviteFragment();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

