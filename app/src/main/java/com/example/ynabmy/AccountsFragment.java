package com.example.ynabmy;

import android.accounts.Account;
import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsFragment extends Fragment {
    private Button allAccountBtn;

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

        allAccountBtn = view.findViewById(R.id.allAccount);
        allAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("xxx", "yyy");
                Toast.makeText(getActivity(),"xxxxx", Toast.LENGTH_LONG).show();
                //openNewActivity();
            }
        });
        return view;
    }
}