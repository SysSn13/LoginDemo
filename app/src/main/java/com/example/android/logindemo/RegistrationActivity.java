package com.example.android.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {


    private EditText UserName ;
    private EditText UserEmail ;
    private EditText UserPassword ;
    private Button RegisterButton;
    private TextView UserLogin;
    private EditText ConfirmUserPassword ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews() ;
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }


    private boolean validate() {

        boolean result = false;
        String Name = UserName.getText().toString();
        String Email = UserEmail.getText().toString();
        String Password = UserPassword.getText().toString();
        String ConfirmPassword = ConfirmUserPassword.getText().toString();
        if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty()){
            Toast.makeText(RegistrationActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
            }
        else result =true;
        return result;
        }


    private void setupUIViews()
    {
        UserName = (EditText) findViewById(R.id.etUserName);
        UserEmail = (EditText) findViewById(R.id.etEmail);
        UserPassword = (EditText) findViewById(R.id.etUserPassword);
        RegisterButton = (Button) findViewById(R.id.btnRegister);
        UserLogin = (TextView) findViewById(R.id.tvLogin);
        ConfirmUserPassword = (EditText) findViewById(R.id.etConfirmPassword);

    }
}
