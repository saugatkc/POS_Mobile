package com.example.pos_mobile.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pos_mobile.R;
import com.example.pos_mobile.fragments.CounterFragment;
import com.example.pos_mobile.fragments.ItemsFragment;
import com.example.pos_mobile.fragments.MoreFragment;
import com.example.pos_mobile.fragments.ReportsFragment;
import com.example.pos_mobile.fragments.TodayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
    BottomNavigationView btmNav;
    DrawerLayout drawer;
    protected ImageView calculator,share,adduser;
    Fragment selectedFragment =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btmNav =findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        calculator=findViewById(R.id.calculator);
        share=findViewById(R.id.share);
        adduser=findViewById(R.id.add_user);
        getSupportActionBar().hide();
        btmNav.setOnNavigationItemSelectedListener(selected_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new CounterFragment()).commit();


//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.counterFragment
//        ).build();
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(btmNav, navController);

        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener selected_menu= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.reportsFragment:
                    selectedFragment = new ReportsFragment();
                    calculator.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.VISIBLE);
                    adduser.setVisibility(View.INVISIBLE);
                    break;
                case R.id.todayFragment:
                    selectedFragment = new TodayFragment();
                    calculator.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.INVISIBLE);
                    adduser.setVisibility(View.INVISIBLE);
                    break;
                case R.id.counterFragment:
                    selectedFragment = new CounterFragment();
                    calculator.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.INVISIBLE);
                    adduser.setVisibility(View.VISIBLE);
                    break;
                case R.id.itemsFragment:
                    selectedFragment = new ItemsFragment();
                    calculator.setVisibility(View.VISIBLE);
                    share.setVisibility(View.INVISIBLE);
                    adduser.setVisibility(View.INVISIBLE);
                    break;
                case R.id.moreFragment:
                    selectedFragment = new MoreFragment();
                    calculator.setVisibility(View.INVISIBLE);
                    share.setVisibility(View.INVISIBLE);
                    adduser.setVisibility(View.INVISIBLE);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    selectedFragment).commit();
            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
            }
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}