package com.example.ashwin.tabbedcustomlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView mCustomTabTextView1, mCustomTabTextView2, mCustomTabTextView3;
    private int[] tabIcons = {
            R.drawable.ic_home_white_18dp,
            R.drawable.ic_favorite_white_18dp,
            R.drawable.ic_build_white_18dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Add icons to tabs
        //setupTabIcons();

        // Set up custom tab
        setUpCustomTab();

        // Initialize first tab selected
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
        //tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(0).getCustomView().setBackgroundColor(tabIconColor);

        // Tab text color normal selected
        tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
                        //tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        tab.getCustomView().setBackgroundColor(tabIconColor);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                        //tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                        tab.getCustomView().setBackgroundColor(tabIconColor);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "ONE");
        adapter.addFragment(new Fragment2(), "FAVOURITES");
        adapter.addFragment(new Fragment3(), "APPLICATION SETTINGS");
        viewPager.setAdapter(adapter);
    }

    private void setUpCustomTab()
    {
        // Tab 1
        mCustomTabTextView1 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        mCustomTabTextView1.setText("HOME");
        mCustomTabTextView1.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[0], 0, 0); //(left, top, right, bottom)
        tabLayout.getTabAt(0).setCustomView(mCustomTabTextView1);

        // Tab 2
        mCustomTabTextView2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        mCustomTabTextView2.setText("FAVOURITES");
        mCustomTabTextView2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, tabIcons[1]); //(left, top, right, bottom)
        tabLayout.getTabAt(1).setCustomView(mCustomTabTextView2);

        // Tab 3
        mCustomTabTextView3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        mCustomTabTextView3.setText("APPLICATION SETTINGS");
        mCustomTabTextView3.setCompoundDrawablesWithIntrinsicBounds(tabIcons[2], 0, 0, 0); //(left, top, right, bottom)
        tabLayout.getTabAt(2).setCustomView(mCustomTabTextView3);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // Tab title text
            return mFragmentTitleList.get(position);

            // Tab title icon
            //return null;
        }
    }
}
