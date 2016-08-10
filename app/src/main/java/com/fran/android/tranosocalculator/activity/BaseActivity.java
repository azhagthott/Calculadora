package com.fran.android.tranosocalculator.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by fran on 10-08-16.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String LOG_TAG = "LOG::: ";
    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


    }
}
