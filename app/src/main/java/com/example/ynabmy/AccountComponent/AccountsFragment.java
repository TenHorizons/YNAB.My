package com.example.ynabmy.AccountComponent;

import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.ynabmy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsFragment extends Fragment {
    private Button allAccountBtn;
    private Button addAccountBtn;
    private ListView obj;

    public AccountsFragment() {
        super(R.layout.fragment_accounts);
    }

    public static AccountsFragment newInstance() {
        AccountsFragment fragment = new AccountsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        MyDBHandler db = new MyDBHandler(getActivity());
//        List<com.example.ynabmy.AccountComponent.Account> accounts = new ArrayList<Account>();
        //accounts = db.getAllAccounts();
        ArrayList accounts = db.getAllAccounts();
        ArrayList <String>budgetAccounts = new ArrayList<String>();
        ArrayList <String>loanAccounts = new ArrayList<String>();
        ArrayList <String>trackingAccounts = new ArrayList<String>();

        for (int i = 0 ; i < accounts.size() ; i++) {
            Account accountClass = (Account) accounts.get(i);
            String accType = accountClass.getBudgetType().trim().split("-")[0];
            String listviewText = accountClass.getNickname() + " - $" + accountClass.getBalance();
            if (accType.equals("Budget Accounts ")) {
                budgetAccounts.add((String)listviewText);
            } else if (accType.equals("Mortgage and Loans ")) {
                loanAccounts.add((String)listviewText);
            } else if (accType.equals("Tracking Accounts ")) {
                trackingAccounts.add((String)listviewText);
            }
            Log.d(String.valueOf(i), (String) accType);
            Log.d(String.valueOf(i), (String) listviewText);

        }

        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(getActivity(),R.layout.listview_item, budgetAccounts);
        ArrayAdapter arrayAdapter2=new ArrayAdapter<String>(getActivity(),R.layout.listview_item, loanAccounts);
        ArrayAdapter arrayAdapter3=new ArrayAdapter<String>(getActivity(),R.layout.listview_item, trackingAccounts);

        ListView listView = view.findViewById(R.id.budget_listview);
        listView.setAdapter(arrayAdapter);

        ListView listView2 = view.findViewById(R.id.loan_listview);
        listView2.setAdapter(arrayAdapter2);

        ListView listView3 = view.findViewById(R.id.tracking_listview);
        listView3.setAdapter(arrayAdapter3);

        allAccountBtn = view.findViewById(R.id.allAccount);
        allAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAllAccountActivity();
            }
        });

        addAccountBtn = view.findViewById(R.id.addAccount);
        addAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAddAccountActivity();
            }
        });


        return view;
    }

    public void openAddAccountActivity(){
        Intent intent = new Intent(getActivity(), AddAccount.class);
        startActivity(intent);
    }

    public void openAllAccountActivity(){
        Intent intent = new Intent(getActivity(), AllAccounts.class);
        startActivity(intent);
    }
}