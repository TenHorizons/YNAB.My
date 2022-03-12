package com.example.ynabmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                Object item = adapterView.getItemAtPosition(i);
                if (item != null) {
                    //Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_LONG).show();
                    String itemString = item.toString();
                    String[] parts = itemString.trim().split("-");
                    String budgetType = parts[0];
                    //Toast.makeText(getApplicationContext(), budgetType, Toast.LENGTH_LONG).show();
                    if (budgetType.equals("Mortgage and Loans ")) {
                        //Toast.makeText(getApplicationContext(), "MortgageandLoans", Toast.LENGTH_LONG).show();
                        TextView tv1 = (TextView)findViewById(R.id.interest_rate_text);
                        tv1.setVisibility(View.VISIBLE);
                        TextView tv2 = (TextView)findViewById(R.id.monthly_payment_text);
                        tv2.setVisibility(View.VISIBLE);
                        EditText et1 = (EditText)findViewById(R.id.interest_rate);
                        et1.setVisibility(View.VISIBLE);
                        EditText et2 = (EditText)findViewById(R.id.monthly_payment);
                        et2.setVisibility(View.VISIBLE);
                    } else {
                        //Toast.makeText(getApplicationContext(), "Others", Toast.LENGTH_LONG).show();
                        TextView tv1 = (TextView)findViewById(R.id.interest_rate_text);
                        tv1.setVisibility(View.INVISIBLE);
                        TextView tv2 = (TextView)findViewById(R.id.monthly_payment_text);
                        tv2.setVisibility(View.INVISIBLE);
                        EditText et1 = (EditText)findViewById(R.id.interest_rate);
                        et1.setVisibility(View.INVISIBLE);
                        EditText et2 = (EditText)findViewById(R.id.monthly_payment);
                        et2.setVisibility(View.INVISIBLE);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void addAccountNext(View view) {

        Spinner budget_type = (Spinner)findViewById(R.id.account_type_spinner);
        EditText nickname = (EditText)findViewById(R.id.nickname);
        EditText account_balance = (EditText)findViewById(R.id.account_balance);
        EditText interest_rate = (EditText)findViewById(R.id.interest_rate);
        EditText monthly_payment = (EditText)findViewById(R.id.monthly_payment);

//        Toast.makeText(getApplicationContext(),budget_type.getSelectedItem().toString() , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),nickname.getText().toString() , Toast.LENGTH_LONG).show();

        Float interest_rate_f;
        Float monthly_payment_f;
        if (interest_rate.getText().toString().trim().isEmpty()) {
            interest_rate_f = 0.0f;
        } else {
            interest_rate_f = Float.parseFloat(interest_rate.getText().toString());
        }

        if (monthly_payment.getText().toString().trim().isEmpty()) {
            monthly_payment_f = 0.0f;
        } else {
            monthly_payment_f = Float.parseFloat(monthly_payment.getText().toString());
        }

//
//        Toast.makeText(getApplicationContext(),account_balance.getText().toString() , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),Float.toString(interest_rate_f) , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),Float.toString(monthly_payment_f) , Toast.LENGTH_LONG).show();

        MyDBHandler db = new MyDBHandler(this);
        long account_id = db.createAccount(budget_type.getSelectedItem().toString(), nickname.getText().toString(), Float.parseFloat(account_balance.getText().toString()), interest_rate_f, monthly_payment_f);
        if (account_id > -1) {
            Toast.makeText(getApplicationContext(), "Budget added successfully , row Id = " + Long.toString(account_id), Toast.LENGTH_LONG).show();
        } else if (account_id == -1) {
            Toast.makeText(getApplicationContext(), "Database entry creation fail", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

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