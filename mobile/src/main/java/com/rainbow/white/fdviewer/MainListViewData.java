package com.rainbow.white.fdviewer;

import android.graphics.drawable.Drawable;

import java.util.Calendar;
import java.util.Date;

public class MainListViewData {
    private Drawable mImage;
    private String mTitle;
    private Date mDate;


    public MainListViewData () {
        this.mDate = Calendar.getInstance().getTime();
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable img) {
        this.mImage = img;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }



}
