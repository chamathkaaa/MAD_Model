package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {

    EditText username, dob, password;
    RadioButton male,female;
    Button updateProfile, add;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.usernamePM);
        dob = findViewById(R.id.dobPM);
        password = findViewById(R.id.passwordPM);

        male = findViewById(R.id.MalePM);
        female = findViewById(R.id.FemalePM);

        updateProfile = findViewById(R.id.updateBtnPM);
        add = findViewById(R.id.addBtnPM);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male.isChecked()){
                    gender = "Male";
                }else{
                    gender = "Female";
                }

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                long newID = dbHelper.addInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);

                /*if(newID > 0){
                    Toast.makeText(ProfileManagement.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ProfileManagement.this, "User Not Added", Toast.LENGTH_SHORT).show();
                }*/

                Toast.makeText(ProfileManagement.this, "User Added Successfully "+newID, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
