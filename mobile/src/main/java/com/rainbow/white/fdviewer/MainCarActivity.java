package com.rainbow.white.fdviewer;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.apps.auto.sdk.CarActivity;

public class MainCarActivity extends CarActivity implements View.OnTouchListener {
    private static final String TAG = "MainCarActivity";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
