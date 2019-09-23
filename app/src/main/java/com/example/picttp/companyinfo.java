package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class companyinfo extends AppCompatActivity {

    private TextView comp,ctc,role,type,url;
    private DatabaseReference mUsersDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyinfo);
        final String user_ID = getIntent().getStringExtra("user_id");
        comp = (TextView) findViewById(R.id.request_compname);
        ctc = (TextView)findViewById(R.id.request_ctc);
        role = (TextView)findViewById(R.id.request_role);
        type = (TextView)findViewById(R.id.request_type);
        url = (TextView)findViewById(R.id.request_url);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("company").child(user_ID);

        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String Comp = dataSnapshot.child("comp_name").getValue().toString();
                String Ctc = dataSnapshot.child("ctc").getValue().toString();
                String Role = dataSnapshot.child("role").getValue().toString();
                String Type = dataSnapshot.child("type").getValue().toString();
                String Url = dataSnapshot.child("url").getValue().toString();

                comp.setText(Comp);
                ctc.setText(Ctc);
                role.setText(Role);
                type.setText(Type);
                url.setText(Url);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
