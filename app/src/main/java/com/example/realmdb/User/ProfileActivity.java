package com.example.realmdb.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.realmdb.R;

public class ProfileActivity extends AppCompatActivity {
    EditText username, name, emailaAddress, gender, birthyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username= (EditText) findViewById(R.id.usernameEditText);
        name= (EditText) findViewById(R.id.nameEdittext);
        emailaAddress= (EditText) findViewById(R.id.emailEdittext);
        gender= (EditText) findViewById(R.id.genderEdittexx);
        birthyear= (EditText) findViewById(R.id.birthyearEdittext);
    }
}
