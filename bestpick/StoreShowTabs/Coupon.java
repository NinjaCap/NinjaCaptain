package com.bestpickindia.android.bestpick.StoreShowTabs;

import java.util.ArrayList;

/**
 * Created by HP on 6/25/2016.
 */
public class Coupon {
    private String store;
    private String thumbnailUrl;
    private  String coupontitle;

    public String getStore() {
        return store;
    }

    public void setStore(String storename) {
        this.store = storename;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    //private int year;

    public String getCoupontitle() {
        return coupontitle;
    }

    public void setCoupontitle(String coupontitle) {
        this.coupontitle = coupontitle;
    }


}
