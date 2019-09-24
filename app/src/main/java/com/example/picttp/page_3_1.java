package com.example.picttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.storage.FirebaseStorage;

public class page_3_1 extends AppCompatActivity {

    //FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_1);
    }
}
