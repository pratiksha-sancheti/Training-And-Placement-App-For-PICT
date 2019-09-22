package com.example.picttp;

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
import com.google.firebase.auth.FirebaseUser;

public class LOGIN_FORM extends AppCompatActivity {

    private EditText mail_id;
    private EditText pass;
    private TextView userRegister;
    private EditText Name;
    private EditText Password;
    private Button Login;
    private FirebaseAuth firebase;
    private ProgressDialog progressDialog;


    @Override
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login");
        mail_id=(EditText)findViewById(R.id.EtName);
        pass = (EditText)findViewById(R.id.EtPassword);
       //
        // userRegister = (TextView)findViewById(R.id.tvRegister);

    }

    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),SIGNUP_FORM.class));
    }
    //public void btn_login(View view) {
      //  startActivity(new Intent(getApplicationContext(),page_3.class));
    //}
*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login");

        Name = (EditText)findViewById(R.id.EtName);
        Password = (EditText)findViewById(R.id.EtPassword);
        Login = (Button)findViewById(R.id.etlogin);

        firebase = FirebaseAuth.getInstance();

        FirebaseUser user = firebase.getCurrentUser();

        progressDialog = new ProgressDialog(this);
        if(user != null)
        {
            finish();
            startActivity(new Intent(LOGIN_FORM.this,page_3.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate(Name.getText().toString() , Password.getText().toString());
            }
        });


    }
    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),SIGNUP_FORM.class));
    }
    private void Validate(String UserName , String UserPassword)
    {
        progressDialog.setMessage("");

        firebase.signInWithEmailAndPassword(UserName,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LOGIN_FORM.this , "Login Successful" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LOGIN_FORM.this,page_3.class));
                }
                else{
                    Toast.makeText(LOGIN_FORM.this , "Login Failed" , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
