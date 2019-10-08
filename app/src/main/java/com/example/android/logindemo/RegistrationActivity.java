package com.example.android.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {


    private EditText UserName ;
    private EditText UserEmail ;
    private EditText UserPassword ;
    private Button RegisterButton;
    private TextView UserLogin;
    private EditText ConfirmUserPassword ;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews() ;
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                    //Upload data to the database
                    progressDialog.setMessage("Creating new Account");
                    progressDialog.show();
                    String user_email = UserEmail.getText().toString().trim();
                    String user_password = UserPassword.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class ));
                            }
                            else  {
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this,"Registration failed!! ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    
                }
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
            Toast.makeText(RegistrationActivity.this,"Please fill in all required fields",Toast.LENGTH_SHORT).show();
            }
        else if(!(Password.equals(ConfirmPassword))){
            Toast.makeText(RegistrationActivity.this,"Password confirmation doesn't match",Toast.LENGTH_SHORT).show();
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
