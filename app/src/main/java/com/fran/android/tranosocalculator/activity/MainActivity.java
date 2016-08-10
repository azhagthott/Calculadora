package com.fran.android.tranosocalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fran.android.tranosocalculator.R;
import com.fran.android.tranosocalculator.fragment.MainDataFragment;
import com.fran.android.tranosocalculator.fragment.MovesetFragment;
import com.fran.android.tranosocalculator.fragment.TotalPowerFragment;
import com.fran.android.tranosocalculator.fragment.TotalPowerPFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private String pokemonName;

    private static final int NUM_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPager = (ViewPager) findViewById(R.id.container);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private String tabTitles[] = new String[]{
                getResources().getString(R.string.tab_main_data),
                getResources().getString(R.string.moveset),
                getResources().getString(R.string.tab_total_power),
                getResources().getString(R.string.tab_total_power_p)
        };

        private Bundle arg1;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {


            Bundle extras = getIntent().getExtras();

            if (extras != null) {
                pokemonName = extras.getString("POKEMON_NAME");
                arg1 = new Bundle();
                arg1.putString("POKEMON_NAME", pokemonName);
            }

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new MainDataFragment();
                    fragment.setArguments(arg1);
                    break;
                case 1:
                    fragment = new MovesetFragment();
                    fragment.setArguments(arg1);
                    break;
                case 2:
                    fragment = new TotalPowerFragment();
                    fragment.setArguments(arg1);
                    break;
                case 3:
                    fragment = new TotalPowerPFragment();
                    fragment.setArguments(arg1);
                    break;
            }

            return fragment;
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
