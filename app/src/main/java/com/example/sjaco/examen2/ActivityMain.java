package com.example.sjaco.examen2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sjaco.examen2.tools.Constant;


public class ActivityMain extends AppCompatActivity {

    private static final int TOTAL_PAGES = 3;
    private FragmentTechnology fragmentTechnology;
    private FragmentHome fragmentHome;
    private FragmentElectronics fragmentElectronics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabLayout = findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton add = findViewById(R.id.activity_main_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityItem.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_logout:
                logout();
                break;
            case R.id.action_privacy:
                Intent intent = new Intent(this,
                        ActivityPrivacyPolicy.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        SharedPreferences sharedPreferences = getSharedPreferences(
                "com.iteso.USER_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(this,
                ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case Constant.FRAGMENT_TECHNOLOGY:
                    if (fragmentTechnology == null)
                        fragmentTechnology = new FragmentTechnology();
                    return fragmentTechnology;
                case Constant.FRAGMENT_HOME:
                    if (fragmentHome == null)
                        fragmentHome = new FragmentHome();
                    return fragmentHome;
                case Constant.FRAGMENT_ELECTRONICS:
                    if (fragmentElectronics == null)
                        fragmentElectronics = new FragmentElectronics();
                    return fragmentElectronics;
                default:
                    return new FragmentTechnology();
            }
        }

        @Override
        public int getCount() {
            return TOTAL_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case Constant.FRAGMENT_TECHNOLOGY:
                    return getString(R.string.section1);
                case Constant.FRAGMENT_HOME:
                    return getString(R.string.section2);
                case Constant.FRAGMENT_ELECTRONICS:
                    return getString(R.string.section3);
            }
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.ACTIVITY_DETAIL:
                if(resultCode == RESULT_OK){
                    if(data.getExtras() != null) {
                        int fragment = data.getExtras().getInt(Constant.EXTRA_FRAGMENT);
                        switch (fragment) {
                            case Constant.FRAGMENT_TECHNOLOGY:
                                fragmentTechnology.onActivityResult(requestCode, resultCode, data);
                                break;
                            case Constant.FRAGMENT_HOME:
                                fragmentHome.onActivityResult(requestCode, resultCode, data);
                                break;
                            case Constant.FRAGMENT_ELECTRONICS:
                                fragmentElectronics.onActivityResult(requestCode, resultCode, data);
                                break;
                        }
                    }
                }
                break;
        }
    }
}







