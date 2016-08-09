package com.fran.android.tranosocalculator.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fran.android.tranosocalculator.R;

public class TotalPowerPFragment extends Fragment {

    public TotalPowerPFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_total_power_p, container, false);
        return rootView;
    }
}
