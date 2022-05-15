package com.example.ynabmy.LoginComponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ynabmy.MainActivity;
import com.example.ynabmy.R;
import com.example.ynabmy.UserComponent.User;
import com.example.ynabmy.UserComponent.UserDBHandler;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void checkLoginDetails(View view) {
        UserDBHandler db = new UserDBHandler(this);
        String username = ((TextView)findViewById(R.id.usernameTxt)).getText().toString();
        String password = ((TextView)findViewById(R.id.passwordTxt)).getText().toString();
        if(db.checkUser(username,password)){
            int userId = db.getUser(username,password,this);
            if(userId != -1){
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user",userId);
                startActivity(intent);
            }

        }
        //say user type wrong credentials;
    }
}