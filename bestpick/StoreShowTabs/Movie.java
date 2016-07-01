package com.bestpickindia.android.bestpick.StoreShowTabs;

import java.util.ArrayList;

/**
 * Created by HP on 6/24/2016.
 */
public class Movie {
    private String storename, thumbnailUrl;
    //private int year;
    private String category;
    private ArrayList<String> cashback;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, String category,
                 ArrayList<String> cashback) {
        this.storename = name;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
        this.cashback = cashback;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String name) {
        this.storename = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /*public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String rating) {
        this.category = rating;
    }

    public ArrayList<String> getCashback() {
        return cashback;
    }

    public void setCashback(ArrayList<String> genre) {
        this.cashback = genre;
    }
}
