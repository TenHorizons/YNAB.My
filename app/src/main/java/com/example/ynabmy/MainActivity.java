package com.example.ynabmy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.budget_toolbar));
        initiateActionBar(getActionBar());
    }
    private void initiateActionBar(ActionBar actionBar){

    }
}