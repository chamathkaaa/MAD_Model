package com.example.modelpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.modelpaper.Database.DBHelper;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username, dob, password;
    RadioButton male, female;
    Button search, edit, delete;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.usernameE);
        dob = findViewById(R.id.dobE);
        password = findViewById(R.id.passwordE);

        male = findViewById(R.id.MaleE);
        female = findViewById(R.id.FemaleE);

        search = findViewById(R.id.searchBtnE);
        edit = findViewById(R.id.editBtnE);
        delete = findViewById(R.id.deleteBtnE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                List user = dbHelper.readAllInfor(username.getText().toString());

                if(user.isEmpty()){
                    Toast.makeText(EditProfile.this, "No User Found", Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }else{
                    username.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());

                    if(user.get(3).toString().equals("Male")){
                        male.setChecked(true);
                    }else{
                        female.setChecked(true);
                    }

                    Toast.makeText(EditProfile.this, "User Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                if(male.isChecked()){
                    gender = "Male";

                }else{
                    gender = "Female";
                }

                boolean isUpdated = dbHelper.updateInfor(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);

                if(isUpdated == true){
                    Toast.makeText(EditProfile.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditProfile.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                int isDeleted = dbHelper.deleteInfo(username.getText().toString());

                if(isDeleted>0){
                    Toast.makeText(EditProfile.this, "User Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditProfile.this, "User Not Deleted", Toast.LENGTH_SHORT).show();
                }

                username.setText("");
                dob.setText("");
                password.setText("");
                male.setChecked(false);
                female.setChecked(false);
                
            }
        });
    }
}
