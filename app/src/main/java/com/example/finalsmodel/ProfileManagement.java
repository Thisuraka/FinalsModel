package com.example.finalsmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ProfileManagement extends AppCompatActivity {

    DBHelper dbHelper;
    UserProfile.Users user;

    TextView profMgtUnTv, profMgtDobTv, profMgtPwTv, profMgtGenTv;
    EditText profMgtUnEt, profMgtDobEt, profMgtPwEt;
    RadioButton profMgtRb1, profMgtRb2;
    RadioGroup profMgtRb;
    Button profMgtBtn;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        user = new UserProfile.Users();
        dbHelper = new DBHelper(this);

        profMgtUnTv = findViewById(R.id.profMgtUnTv);
        profMgtDobTv = findViewById(R.id.profMgtDobTv);
        profMgtPwTv = findViewById(R.id.profMgtPwTv);
        profMgtGenTv = findViewById(R.id.profMgtGenTv);
        profMgtUnEt = findViewById(R.id.profMgtUnEt);
        profMgtDobEt = findViewById(R.id.profMgtDobEt);
        profMgtPwEt = findViewById(R.id.profMgtPwEt);
        profMgtRb = findViewById(R.id.profMgtRb);
        profMgtRb1 = findViewById(R.id.profMgtRb1);
        profMgtRb2 = findViewById(R.id.profMgtRb2);
        profMgtBtn = findViewById(R.id.profMgtBtn);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        profMgtUnEt.setText(username);
        profMgtPwEt.setText(password);

        profMgtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditProfile.class);
                startActivity(intent);
                clearControls();

            }
        });

    }

    private void clearControls() {
        profMgtUnEt.setText("");
        profMgtDobEt.setText("");
        profMgtPwEt.setText("");
        profMgtRb1.invalidate();
        profMgtRb2.invalidate();
    }
}