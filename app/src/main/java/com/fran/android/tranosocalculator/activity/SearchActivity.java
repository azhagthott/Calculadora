package com.fran.android.tranosocalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.fran.android.tranosocalculator.R;
import com.fran.android.tranosocalculator.main.Base;
import com.fran.android.tranosocalculator.main.Pokemon;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends Base {

    private String searchName;
    private Pokemon pokemon;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AutoCompleteTextView pokemonName = (AutoCompleteTextView) findViewById(R.id.pokemonName);
        String[] pokemonNames = getResources().getStringArray(R.array.pokemon_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemonNames);
        pokemonName.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pokemonName.getText().equals("")) {
                    dataFromFirebase(pokemonName.getText().toString());
                }
            }
        });

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });

    }

    private void dataFromFirebase(final String pokemonName) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("jsonData");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    searchName = dataSnapshot.child("" + i + "").child("name").getValue().toString();

                    if (searchName.equals(pokemonName)) {

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

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                FirebaseCrash.log("DatabaseError: " + databaseError);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
