package com.example.picttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class page_3_2_admin extends AppCompatActivity {

    private EditText name_comp,ctc,type,role,url;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String Name_Comp,Ctc,Type,Role,Url;
    private Button addcompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_2_admin);
        getSupportActionBar().setTitle("Add Company");
        setupuiviews();
        addcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("case1", "onClick: onclick");

                if(validate())

                {
                    Log.d("case11", "onClick: onclick");
                    sendcompanydata();
                   Toast.makeText(page_3_2_admin.this, "Company Added Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(page_3_2_admin.this , page_3_admin.class));
                }
                else
                    Toast.makeText(page_3_2_admin.this, "Failed to add Company", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupuiviews()
    {
        name_comp = (EditText)findViewById(R.id.name_comp);
        ctc = (EditText)findViewById(R.id.ctc);
        type = (EditText)findViewById(R.id.type);
        role = (EditText)findViewById(R.id.role);
        url = (EditText)findViewById(R.id.url);
        addcompany = (Button)findViewById(R.id.etsubmit);
    }
    private boolean validate()
    {
        boolean result=false;
        Name_Comp = name_comp.getText().toString();
        Ctc = ctc.getText().toString();
        Type = type.getText().toString();
        Role = role.getText().toString();
        Url = url.getText().toString();
        if(Name_Comp.isEmpty() || Ctc.isEmpty() || Type.isEmpty() || Role.isEmpty() || Url.isEmpty())
        {
            Toast.makeText(this, "Enter all Details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        Log.d("result11", "validate: "+result);
        return result;
    }
    private void sendcompanydata()
    {
        String name = Name_Comp;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Company");
        comapnyinfo cmpinfo =  new comapnyinfo(Name_Comp,Ctc,Type,Role,Url);
        myRef.child("Company").setValue(cmpinfo);

    }

}
