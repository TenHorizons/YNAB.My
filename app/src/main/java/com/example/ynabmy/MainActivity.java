package com.example.ynabmy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateBottomNav();

        //##moving this to budget fragment soon.
            //since only budget fragment has option menu,
            //planning on adding hasOptionMenu(true/false) somewhere in the future
            //initiateActionBar(getSupportActionBar());
    }

    private void initiateBottomNav(){
        //initiate bottom navigation with navHostFragment
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());
    }

    //##moving this to budget fragment soon.
//    private void initiateActionBar(androidx.appcompat.app.ActionBar supportActionBar) {
//        //to add dateTime picker and option menu
//    }

//    public void showDatePickerDialog(View v) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }


    //##moving this to budget fragment soon.
//    //option items to the right of budget action bar
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_edit:
//                actionEdit();
//                return true;
//            case R.id.action_settings:
//                actionSettings();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    //option item: edit
//    private void actionEdit(){
//
//    }
//
//    //option item: settings
//    private void actionSettings(){
//
//    }
}