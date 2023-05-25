package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.databinding.ActivityDashboardBinding;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.fragements.HomeFragment;
import com.example.myapplication.fragements.ProfileFragment;
import com.example.myapplication.fragements.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    private void loadFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.dashboard_container,
                f).commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBManager mgr = new DBManager(DashboardActivity.this);
//        mgr.saveDepartment("HR");
//        mgr.saveDepartment("Finance");
//        mgr.saveDepartment("Marketing");

        loadFragment(new HomeFragment());

       binding.bottomNavDashboard.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(R.id.action_home == item.getItemId()){
                   loadFragment(new HomeFragment());
               }else if(R.id.action_profile == item.getItemId()){
                   loadFragment(new ProfileFragment());
               }else{
                  loadFragment(new SettingFragment());
               }
               return true;
           }
       });
        //get the intent
        Intent objIntent = getIntent();
        //get bundle
        Bundle bObj = objIntent.getExtras();
        //get data using key
        String id = bObj.getString("ID");
        String pwd = bObj.getString("PWD");
        //process data
        this.setTitle("Welcome : "+id);
    }
}