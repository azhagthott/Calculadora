package com.fran.android.tranosocalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.fran.android.tranosocalculator.R;
import com.fran.android.tranosocalculator.main.Base;

public class SearchActivity extends Base {

    private String pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AutoCompleteTextView pokemonName = (AutoCompleteTextView) findViewById(R.id.pokemonName);
        final String[] pokemonNames = getResources().getStringArray(R.array.pokemon_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemonNames);
        pokemonName.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pokemonName.getText().equals("")) {

                    pokemon = pokemonName.getText().toString();
                    Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                    intent.putExtra("POKEMON_NAME", pokemon);
                    startActivity(intent);
                }
            }
        });
    }
}
