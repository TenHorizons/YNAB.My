package com.example.ynabmy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        Spinner spinner = (Spinner) findViewById(R.id.account_type_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.account_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                // Your code here
//                Toast.makeText(getApplicationContext(),i, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }


    public void addAccountNext(View view) {
        Toast.makeText(getApplicationContext(),"next move", Toast.LENGTH_LONG).show();
    }

    public void checkNextBtnEnable(View view) {

        EditText nickname=(EditText)findViewById(R.id.nickname);

        EditText accountBalance=(EditText)findViewById(R.id.account_balance);

        Spinner mySpinner = (Spinner) findViewById(R.id.account_type_spinner);
        String spinnerText = mySpinner.getSelectedItem().toString();

        if (nickname.getText().toString().trim().isEmpty() || accountBalance.getText().toString().trim().isEmpty() || spinnerText.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please fill in the blanks", Toast.LENGTH_LONG).show();
        } else {
            addAccountNext(view);
        }

    }

}