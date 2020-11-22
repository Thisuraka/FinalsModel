package com.example.finalsmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    DBHelper dbHelper;
    UserProfile.Users user;

    TextView homUnTv, homPwTv;
    EditText homUnEt, homPwEt;
    Button homLogBtn, homRegBtn;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);

        homUnTv = findViewById(R.id.homUnTv);
        homPwTv = findViewById(R.id.homPwTv);
        homUnEt = findViewById(R.id.homUnEt);
        homPwEt = findViewById(R.id.homPwEt);
        homLogBtn = findViewById(R.id.homLogBtn);
        homRegBtn = findViewById(R.id.homRegBtn);

        homLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditProfile.class);
                startActivity(intent);
            }
        });

        homRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = homUnEt.getText().toString();
                password = homPwEt.getText().toString();

                UserProfile.Users user = new UserProfile.Users(username, password, null, null);
                long status = dbHelper.addInfo(user);

                if (status > 0) {
                    Toast.makeText(Home.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Home.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getBaseContext(), ProfileManagement.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                clearControls();
            }
        });

    }

    private void clearControls() {
        homUnEt.setText("");
        homPwEt.setText("");
    }

}