package com.fran.android.tranosocalculator.data;

import android.util.Log;

import com.fran.android.tranosocalculator.main.Pokemon;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.fran.android.tranosocalculator.main.Base.LOG_TAG;

/**
 * Created by fran on 08-08-16.
 */

public class GetFirebaseData {

    private Pokemon pokemon;

    public GetFirebaseData() {
    }

    public Pokemon getPokemonData(final String pokemonName) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("jsonData");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    String searchName = dataSnapshot.child("" + i + "").child("name").getValue().toString();

                    Log.d(LOG_TAG, "searchName: " + searchName);
                    Log.d(LOG_TAG, "pokemonName: " + pokemonName);

                    if (searchName.equals(pokemonName)) {
                        String id = dataSnapshot.child("" + i + "").child("id").getValue().toString();
                        String name = dataSnapshot.child("" + i + "").child("name").getValue().toString();
                        String type_1 = dataSnapshot.child("" + i + "").child("type_1").getValue().toString();
                        String type_2 = dataSnapshot.child("" + i + "").child("type_2").getValue().toString();

                        Log.d(LOG_TAG, "id: " + id);
                        Log.d(LOG_TAG, "name: " + name);
                        Log.d(LOG_TAG, "type_1: " + type_1);
                        Log.d(LOG_TAG, "type_2: " + type_2);

                        pokemon = new Pokemon(id, name, type_1, type_2);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                FirebaseCrash.log("DatabaseError: " + databaseError);
            }
        });


        Log.d(LOG_TAG, "pokemon id: " + pokemon.getId());

        return pokemon;
    }
}
