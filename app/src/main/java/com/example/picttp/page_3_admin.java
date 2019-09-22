package com.example.picttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class page_3_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_admin);
    }
    public void btn_viewstudents(View view) {
        startActivity(new Intent(this,page_3_1_admin.class));
    }
    public void btn_addcompany(View view) {
        startActivity(new Intent(this,page_3_2_admin.class));
    }
    public void btn_viewcompany(View view) {
        startActivity(new Intent(this,page_3_3_admin.class));
    }
    public void btn_logout(View view) {

        startActivity(new Intent(this,LOGIN_FORM_ADMIN.class));
    }


}
