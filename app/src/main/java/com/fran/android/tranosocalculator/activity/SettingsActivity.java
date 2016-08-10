package com.fran.android.tranosocalculator.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.fran.android.tranosocalculator.R;

/**
 * Created by fran on 09-08-16.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
