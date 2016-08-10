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


public class MovesetFragment extends Fragment {

    private String pokemonName;

    private TextView moveset_quick1_special_move_1;
    private TextView moveset_quick1_special_move_2;
    private TextView moveset_quick1_special_move_3;
    private TextView moveset_quick1_special_move_4;

    private TextView moveset_quick2_special_move_1;
    private TextView moveset_quick2_special_move_2;
    private TextView moveset_quick2_special_move_3;
    private TextView moveset_quick2_special_move_4;

    public MovesetFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_moveset, container, false);

        Bundle bundle = this.getArguments();
        pokemonName = bundle.getString("POKEMON_NAME");

        moveset_quick1_special_move_1 = (TextView) rootView.findViewById(R.id.moveset_quick1_special_move_1);
        moveset_quick1_special_move_2 = (TextView) rootView.findViewById(R.id.moveset_quick1_special_move_2);
        moveset_quick1_special_move_3 = (TextView) rootView.findViewById(R.id.moveset_quick1_special_move_3);
        moveset_quick1_special_move_4 = (TextView) rootView.findViewById(R.id.moveset_quick1_special_move_4);

        moveset_quick2_special_move_1 = (TextView) rootView.findViewById(R.id.moveset_quick2_special_move_1);
        moveset_quick2_special_move_2 = (TextView) rootView.findViewById(R.id.moveset_quick2_special_move_2);
        moveset_quick2_special_move_3 = (TextView) rootView.findViewById(R.id.moveset_quick2_special_move_3);
        moveset_quick2_special_move_4 = (TextView) rootView.findViewById(R.id.moveset_quick2_special_move_4);

        dataFromFirebase(pokemonName);

        return rootView;
    }

    private void dataFromFirebase(final String name) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("jsonData");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    String searchName = dataSnapshot.child("" + i + "").child("name").getValue().toString();

                    if (searchName.equals(name)) {

                        String moveset_quick1_special_move_1_str = dataSnapshot.child("" + i + "").child("moveset_quick1_special_move_1").getValue().toString();
                        String moveset_quick1_special_move_2_str = dataSnapshot.child("" + i + "").child("moveset_quick1_special_move_2").getValue().toString();
                        String moveset_quick1_special_move_3_str = dataSnapshot.child("" + i + "").child("moveset_quick1_special_move_3").getValue().toString();
                        String moveset_quick1_special_move_4_str = dataSnapshot.child("" + i + "").child("moveset_quick1_special_move_4").getValue().toString();

                        String moveset_quick2_special_move_1_str = dataSnapshot.child("" + i + "").child("moveset_quick2_special_move_1").getValue().toString();
                        String moveset_quick2_special_move_2_str = dataSnapshot.child("" + i + "").child("moveset_quick2_special_move_2").getValue().toString();
                        String moveset_quick2_special_move_3_str = dataSnapshot.child("" + i + "").child("moveset_quick2_special_move_3").getValue().toString();
                        String moveset_quick2_special_move_4_str = dataSnapshot.child("" + i + "").child("moveset_quick2_special_move_4").getValue().toString();

                        moveset_quick1_special_move_1.setText(moveset_quick1_special_move_1_str);
                        moveset_quick1_special_move_2.setText(moveset_quick1_special_move_2_str);
                        moveset_quick1_special_move_3.setText(moveset_quick1_special_move_3_str);
                        moveset_quick1_special_move_4.setText(moveset_quick1_special_move_4_str);

                        moveset_quick2_special_move_1.setText(moveset_quick2_special_move_1_str);
                        moveset_quick2_special_move_2.setText(moveset_quick2_special_move_2_str);
                        moveset_quick2_special_move_3.setText(moveset_quick2_special_move_3_str);
                        moveset_quick2_special_move_4.setText(moveset_quick2_special_move_4_str);

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
