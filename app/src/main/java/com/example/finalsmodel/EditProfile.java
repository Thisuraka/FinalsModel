package com.example.finalsmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    DBHelper dbHelper;
    UserProfile.Users user;

    TextView EdProfUnTv, EdProfDobTv, EdProfPwTv, EdProfGenTv;
    EditText EdProfUnEt, EdProfDobEt, EdProfPwEt;
    RadioButton EdProfRb1, EdProfRb2, genderBtn;
    RadioGroup EdProfRb;
    Button EdProfEditBtn, EdProfDelBtn, EdProfSearBtn;

    String name, password, gender, dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        dbHelper = new DBHelper(this);

        EdProfUnTv = findViewById(R.id.EdProfUnTv);
        EdProfDobTv = findViewById(R.id.EdProfDobTv);
        EdProfPwTv = findViewById(R.id.EdProfPwTv);
        EdProfGenTv = findViewById(R.id.EdProfGenTv);
        EdProfUnEt = findViewById(R.id.EdProfUnEt);
        EdProfDobEt = findViewById(R.id.EdProfDobEt);
        EdProfPwEt = findViewById(R.id.EdProfPwEt);
        EdProfRb = findViewById(R.id.EdProfRb);
        EdProfRb1 = findViewById(R.id.EdProfRb1);
        EdProfRb2 = findViewById(R.id.EdProfRb2);
        EdProfEditBtn = findViewById(R.id.EdProfEditBtn);
        EdProfDelBtn = findViewById(R.id.EdProfDelBtn);
        EdProfSearBtn = findViewById(R.id.EdProfSearBtn);

        EdProfSearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = EdProfUnEt.getText().toString();

                List user = dbHelper.readAllInfo(name);
                Log.i("Tag", String.valueOf(user));

                if (user.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No user", Toast.LENGTH_SHORT).show();
                    EdProfUnEt.setText(null);
                    EdProfPwEt.setText(null);
                    EdProfDobEt.setText(null);
                    EdProfRb1.setChecked(false);
                    EdProfRb2.setChecked(false);
                } else {
                    Toast.makeText(getApplicationContext(), "User Found", Toast.LENGTH_SHORT).show();
                    EdProfUnEt.setText(user.get(0).toString());
                    EdProfPwEt.setText(user.get(1).toString());
                    EdProfDobEt.setText(user.get(2).toString());

                    if (user.get(3).toString().equals("Male")) {
                        EdProfRb1.setChecked(true);
                    } else if (user.get(3).toString().equals("Female")) {
                        EdProfRb2.setChecked(true);
                    }

                }
            }
        });

        EdProfEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = EdProfUnEt.getText().toString();
                password = EdProfPwEt.getText().toString();
                dob = EdProfDobEt.getText().toString();

                int gender_id = (EdProfRb.getCheckedRadioButtonId());
                genderBtn = findViewById(gender_id);

                gender = genderBtn.getText().toString();


                Boolean status = dbHelper.updateInfo(name, password ,dob, gender);

                if (status){
                    Toast.makeText(EditProfile.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
                clearControls();
            }
        });

        EdProfDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteInfo(name);

                Toast.makeText(EditProfile.this, "User deleted", Toast.LENGTH_SHORT).show();

                EdProfUnEt.setText(null);
                EdProfPwEt.setText(null);
                EdProfDobEt.setText(null);
                EdProfRb1.setChecked(false);
                EdProfRb2.setChecked(false);
                clearControls();
            }
        });
    }

    private void clearControls() {
        EdProfUnEt.setText("");
        EdProfDobEt.setText("");
        EdProfPwEt.setText("");
        EdProfRb1.invalidate();
        EdProfRb2.invalidate();
    }
}