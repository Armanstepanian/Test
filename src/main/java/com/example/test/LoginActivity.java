package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginButton = (Button)findViewById(R.id.loginButton);
        loginEmail = (EditText)findViewById(R.id.loginEmail);
        loginPassword = (EditText)findViewById(R.id.loginPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(loginEmail.getText().toString(),
                        loginPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        Intent intent = new Intent(LoginActivity.this,GroupsActivity.class);
                        startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "wrong email or password ", Toast.LENGTH_LONG).show();
                            loginEmail.setText("");
                            loginPassword.setText("");
                        }
                    }
                });
            }
        });
    }
}
