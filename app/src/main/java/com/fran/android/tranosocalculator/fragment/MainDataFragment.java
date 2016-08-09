package com.fran.android.tranosocalculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fran.android.tranosocalculator.R;

public class MainDataFragment extends Fragment {

    private TextView tvId;
    private TextView tvName;
    private TextView tvType1;
    private TextView tvType2;
    private TextView tvQuickMove1;
    private TextView tvQuickMove2;
    private TextView tvSpecialMove1;
    private TextView tvSpecialMove2;
    private TextView tvSpecialMove3;
    private TextView tvSpecialMove4;

    public MainDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_data, container, false);

        tvId = (TextView) rootView.findViewById(R.id.tvId);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvType1 = (TextView) rootView.findViewById(R.id.tvType1);
        tvType2 = (TextView) rootView.findViewById(R.id.tvType2);
        tvQuickMove1 = (TextView) rootView.findViewById(R.id.tvQuickMove1);
        tvQuickMove2 = (TextView) rootView.findViewById(R.id.tvQuickMove2);
        tvSpecialMove1 = (TextView) rootView.findViewById(R.id.tvSpecialMove1);
        tvSpecialMove2 = (TextView) rootView.findViewById(R.id.tvSpecialMove2);
        tvSpecialMove3 = (TextView) rootView.findViewById(R.id.tvSpecialMove3);
        tvSpecialMove4 = (TextView) rootView.findViewById(R.id.tvSpecialMove4);

        return rootView;
    }
}
