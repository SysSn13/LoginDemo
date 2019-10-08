package com.example.android.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int counter=5;
    private TextView UserSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        Info = (TextView) findViewById(R.id.tvInfo);
        UserSignUp = (TextView) findViewById(R.id.tvSignUp);



        Info.setText("No. of attempts remaining: 5");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(),Password.getText().toString());

            }
        });


        UserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUp = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(SignUp);
            }
        });





    }

    private void validate(String userName, String userPassword) {

        if ((userName.equals("Admin")) && (userPassword.equals("Admin"))) {
            Intent SecondActivity = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(SecondActivity);
        }
        else {
            if((userName.isEmpty()) || (userPassword.isEmpty())) return;
            else counter--;

            Info.setText("No. of attempts remianing: "+ String.valueOf(counter));
            if(counter==0){
                Login.setEnabled(false);
            }



        }
    }
}