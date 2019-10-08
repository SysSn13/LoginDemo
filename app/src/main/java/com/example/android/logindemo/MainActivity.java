package com.example.android.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int counter = 5;
    private TextView UserSignUp;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        Info = (TextView) findViewById(R.id.tvInfo);
        UserSignUp = (TextView) findViewById(R.id.tvSignUp);

        Info.setText("No. of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        /**if(user!=null){
         finish();
         startActivity(new Intent(MainActivity.this,SecondActivity.class));
         }**/


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());

            }
        });


        UserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUp = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(SignUp);
            }
        });


    }

    private void validate( String userName,  String userPassword) {

        progressDialog.setMessage("Validating User");
        progressDialog.show();


        if(!(userName.isEmpty() || userPassword.isEmpty()))firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                } else
                {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Failed!! ", Toast.LENGTH_SHORT).show();
                counter--;

                Info.setText("No. of attempts remianing: " + String.valueOf(counter));
                if (counter == 0) {
                    Info.setText("Restricted!!! Try again after 5 seconds");

                    //This will disable the button when clicked, and enable it again after 5 seconds.
                    Login.setEnabled(false);
                    Timer buttonTimer = new Timer();
                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Login.setEnabled(true);
                                    Info.setText("No. of attempts remaining: 5");
                                    counter =5;
                                }
                            });
                        }
                    }, 5000);
                }
            }}
        });

        else {
            progressDialog.dismiss();

            Toast.makeText(MainActivity.this,"Fill all the required fields ",Toast.LENGTH_SHORT).show();
        }



    }
}