package com.example.picttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class first extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


    }
    public void btn_student(View view) {

        startActivity(new Intent(getApplicationContext(),LOGIN_FORM.class));

    }
    public void btn_admin(View view) {

        startActivity(new Intent(getApplicationContext(),LOGIN_FORM_ADMIN.class));

    }
}
