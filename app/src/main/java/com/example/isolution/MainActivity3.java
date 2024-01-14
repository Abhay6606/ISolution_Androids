package com.example.isolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closedDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        loadFrgment(new firstFragment(),0);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.page1){
                    loadFrgment(new firstFragment(),1);
                } else if (id==R.id.page2) {
                    loadFrgment(new SecondFragment(),1);
                } else if (id==R.id.page3) {
                    loadFrgment(new thirdFragment(),1);

                } else if (id==R.id.page4) {
                    loadFrgment(new forthFragment(),1);

                } else if (id==R.id.page5) {

                } else{

                }
                drawerLayout.closeDrawer(GravityCompat.START);



                return true;
            }
        });
    }

    private void loadFrgment(Fragment fragment,int flag) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        if (flag==0){

            ft.add(R.id.container,fragment);
        }else {
            ft.replace(R.id.container,fragment);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}