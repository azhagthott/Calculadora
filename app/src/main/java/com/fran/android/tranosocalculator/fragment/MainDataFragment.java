package com.fran.android.tranosocalculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fran.android.tranosocalculator.R;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainDataFragment extends Fragment {

    private String pokemonName;

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

        Bundle bundle = this.getArguments();
        pokemonName = bundle.getString("POKEMON_NAME");

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

        //FIXME: obtener los datos desde la db local
        dataFromFirebase(pokemonName);


        return rootView;
    }

    //FIXME: sacar de aqui, crear clase
    private void dataFromFirebase(final String name) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("jsonData");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    String searchName = dataSnapshot.child("" + i + "").child("name").getValue().toString();

                    if (searchName.equals(name)) {

                        String id = dataSnapshot.child("" + i + "").child("id").getValue().toString();
                        String name = dataSnapshot.child("" + i + "").child("name").getValue().toString();
                        String type_1 = dataSnapshot.child("" + i + "").child("type_1").getValue().toString();
                        String type_2 = dataSnapshot.child("" + i + "").child("type_2").getValue().toString();
                        String quick_move_1 = dataSnapshot.child("" + i + "").child("quick_move_1").getValue().toString();
                        String quick_move_2 = dataSnapshot.child("" + i + "").child("quick_move_2").getValue().toString();
                        String special_move_1 = dataSnapshot.child("" + i + "").child("special_move_1").getValue().toString();
                        String special_move_2 = dataSnapshot.child("" + i + "").child("special_move_2").getValue().toString();
                        String special_move_3 = dataSnapshot.child("" + i + "").child("special_move_3").getValue().toString();
                        String special_move_4 = dataSnapshot.child("" + i + "").child("special_move_4").getValue().toString();


                        //FIXME: entragar todo en un arreglo y guardarlo en un db local

                        tvId.setText(id);
                        tvName.setText(name);
                        tvType1.setText(type_1);
                        tvType2.setText(type_2);
                        tvQuickMove1.setText(quick_move_1);
                        tvQuickMove2.setText(quick_move_2);
                        tvSpecialMove1.setText(special_move_1);
                        tvSpecialMove2.setText(special_move_2);
                        tvSpecialMove3.setText(special_move_3);
                        tvSpecialMove4.setText(special_move_4);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                FirebaseCrash.log("DatabaseError: " + databaseError);
            }
        });
    }
}
