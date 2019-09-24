package com.example.picttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
public class page_3 extends AppCompatActivity {
    private FirebaseAuth firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3);
        getSupportActionBar().setTitle("Menu");
    }

    public void btn_docu(View view) {
        startActivity(new Intent(getApplicationContext(),aadhar.class));
    }
    public void btn_Comp_info(View view) {
        startActivity(new Intent(getApplicationContext(),page_3_3_admin.class));
    }
    public void btn_mock_test(View view) {
        startActivity(new Intent(getApplicationContext(),page_3_3.class));

    }
    public void btn_logout(View view)
    {
        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(this,LOGIN_FORM.class));
    }
}
