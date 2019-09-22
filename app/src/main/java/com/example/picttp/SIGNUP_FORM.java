package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class SIGNUP_FORM extends AppCompatActivity {


    private EditText username,userphone,usermis,usermail,userpassword,usercpassword;
    private FirebaseAuth firebaseauth;
    private Button login;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String name,phone,mis,mail,password,cpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("SignUp");
        setupuiviews();
        firebaseauth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate())
                {
                    String useremail = usermail.getText().toString().trim();
                    String userpass = userpassword.getText().toString().trim();

                    firebaseauth.createUserWithEmailAndPassword(useremail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                senduserdata();
                                Toast.makeText(SIGNUP_FORM.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SIGNUP_FORM.this,LOGIN_FORM.class));
                            }

                            else
                                Toast.makeText(SIGNUP_FORM.this,"Registration Failed",Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
    }

    private void setupuiviews()
    {
        username = (EditText)findViewById(R.id.etname);
        userphone = (EditText)findViewById(R.id.etphone);
        usermis = (EditText)findViewById(R.id.etmis);
        usermail = (EditText)findViewById(R.id.etemail);
        userpassword = (EditText)findViewById(R.id.etpassword);
        usercpassword = (EditText)findViewById(R.id.etcpassword);
        login = (Button)findViewById(R.id.etlogin);

    }

    private boolean validate()
    {
        boolean result = false;

         name = username.getText().toString();
         phone = userphone.getText().toString();
         mis = usermis.getText().toString();
         mail = usermail.getText().toString();
         password = userpassword.getText().toString();
         cpassword = usercpassword.getText().toString();

        if(name.isEmpty() || phone.isEmpty() || mis.isEmpty() || mail.isEmpty() || password.isEmpty() || cpassword.isEmpty())
        {
            Toast.makeText(this, "Enter all Details",Toast.LENGTH_SHORT).show();
        }
        if(!password.equals(cpassword))

        {
            Log.d("msg111", "validate: "+password+cpassword);
            Toast.makeText(this,"Incorrect Password",Toast.LENGTH_SHORT).show();
        }
        else
        {

            result = true;

        }
        return result;
    }

    private void senduserdata(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Users");
        userinfo useri = new userinfo(name,phone,mis,mail,password,cpassword);
        myRef.child(firebaseauth.getUid()).setValue(useri);
    }
}
