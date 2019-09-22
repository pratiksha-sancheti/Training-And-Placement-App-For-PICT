package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentinfo extends AppCompatActivity {
    private TextView Fullname,Mis,mobile,mail;
    private DatabaseReference mUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);
        final String user_ID = getIntent().getStringExtra("user_id");
        Fullname = (TextView) findViewById(R.id.request_fullname);
        Mis = (TextView) findViewById(R.id.request_mis);
        mobile = (TextView)findViewById(R.id.request_mobile);
        mail = (TextView)findViewById(R.id.request_mail);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_ID);

        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullname = dataSnapshot.child("username").getValue().toString();
                String mis = dataSnapshot.child("usermis").getValue().toString();
                String Mobile = dataSnapshot.child("userphone").getValue().toString();
                String Mail = dataSnapshot.child("usermail").getValue().toString();

                Fullname.setText(fullname);
                Mis.setText(mis);
                mobile.setText(Mobile);
                mail.setText(Mail);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
