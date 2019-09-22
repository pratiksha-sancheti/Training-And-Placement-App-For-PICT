package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LOGIN_FORM_ADMIN extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private FirebaseAuth firebase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form__admin);
        getSupportActionBar().setTitle("Admin login");

        Name = (EditText)findViewById(R.id.EtName);
        Password = (EditText)findViewById(R.id.EtPassword);
        Login = (Button)findViewById(R.id.etlogin);

        firebase = FirebaseAuth.getInstance();

        FirebaseUser user = firebase.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        if(user != null)
        {
            finish();
            startActivity(new Intent(this,page_3.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate(Name.getText().toString() , Password.getText().toString());
            }
        });
    }
    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),SIGNUP_FORM_ADMIN.class));
    }

    private void Validate(String UserName , String UserPassword)
    {
        progressDialog.setMessage("");


                if(UserName.equals("Admin") && UserPassword.equals("123456")){
                    Toast.makeText(LOGIN_FORM_ADMIN.this , "Login Successful" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LOGIN_FORM_ADMIN.this,page_3_admin.class));
                }
                else{
                    Toast.makeText(LOGIN_FORM_ADMIN.this , "Login Failed" , Toast.LENGTH_SHORT).show();
                }

            }

    }


