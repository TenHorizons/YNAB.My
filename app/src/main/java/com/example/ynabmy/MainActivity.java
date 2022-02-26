package com.example.ynabmy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.budget_toolbar));
        //since only budget fragment has option menu,
        //planning on adding hasOptionMenu(true/false) somewhere in the future
        initiateActionBar(getActionBar());
        initiateBottomNav();
    }
    private void initiateActionBar(ActionBar actionBar){
        //to add dateTime picker
    }

    private void initiateBottomNav(){
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        NavigationBarView.OnItemSelectedListener bottomNavItemSelected = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (bottomNav.getSelectedItemId()){
                    case R.id.bottom_nav_budget:
                        //
                        return true;
                    case R.id.bottom_nav_transaction:
                        //
                        return true;
                    case R.id.bottom_nav_accounts:
                        //
                        return true;
                    default:
                        //go to budget, set budget as selected.
                        return true;
                }
            }
        };
        bottomNav.setOnItemSelectedListener(bottomNavItemSelected);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
                actionEdit();
                return true;
            case R.id.action_settings:
                actionSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void actionEdit(){

    }

    private void actionSettings(){

    }
}