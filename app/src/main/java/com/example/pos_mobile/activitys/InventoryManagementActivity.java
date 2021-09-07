package com.example.pos_mobile.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.pos_mobile.adapters.PagerAdapter;
import com.example.pos_mobile.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class InventoryManagementActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);
        getSupportActionBar().hide();
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        FragmentManager fm = getSupportFragmentManager();
        //adapter = new PagerAdapter(this)
        //adapter= new PagerAdapter(fm, getLifecycle());
        // viewPager.setAdapter(adapter);
        //viewPager.setAdapter(new PagerAdapter(this));
        viewPager.setAdapter(new PagerAdapter(this));

//        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
//        tabLayout.addTab(tabLayout.newTab().setText("CATEGORY"));
//        tabLayout.addTab(tabLayout.newTab().setText("FAVOURITES"));
//        tabLayout.addTab(tabLayout.newTab().setText("MODIFIERS"));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
//
//    }

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: {
                        tab.setText("ALL");
                        break;
                    }
                    case 1: {
                        tab.setText("CATEGORY");
                        break;
                    }
                    case 2:{
                        tab.setText("FAVOURITES");
                        break;
                    }
                    case 3:{
                        tab.setText("MODIFIERS");
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }


}