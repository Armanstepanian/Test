package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import RoomPackage.entity.Users;
import ViewModels.UsersViewModel;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText name_field;
    EditText password_field;
    TextView login;
    LoginTask loginTask;
    private FirebaseAuth mAuth;
    static String name , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        button = (Button)findViewById(R.id.registrationButton);
        login = (TextView) findViewById(R.id.loginText);
        password_field = (EditText) findViewById(R.id.passwordText);
        name_field = (EditText) findViewById(R.id.nameText);
        loginTask = new LoginTask();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                name = name_field.getText().toString();
                password = password_field.getText().toString();
                loginTask.execute();
                Intent intent = new Intent(MainActivity.this,GroupsActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //TODO
//      final Users users = new Users();
//      users.name = name;
//      mAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//          @Override
//          public void onComplete(@NonNull Task<AuthResult> task) {
//              if(task.isSuccessful()){
//                  users.isSynched = true;
//              }
//              else users.isSynched = false;
//          }
//      });
//      return null;
//  }
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//
return null;
        }

    }
}
