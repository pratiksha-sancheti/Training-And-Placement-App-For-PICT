package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class aadhar extends AppCompatActivity {

    Button selectFile,Upload;
    TextView Notification;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri pdfUri;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar);

        firebaseauth = FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();

        selectFile=findViewById(R.id.select_file);
        Upload=findViewById(R.id.upload);
        Notification=findViewById(R.id.notification);

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(aadhar.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {

                        selectpdf();
                }
                else
                    ActivityCompat.requestPermissions(aadhar.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
            }
        });

        Upload.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("case1234", "onClick: Done");
                if(pdfUri!=null) {
                    Log.d("case1234", "onClick: Done_1");
                    uploadfile(pdfUri);
                    Log.d("case1234", "onClick: Done_2");
                }
                else {
                    Toast.makeText(aadhar.this, "Select File", Toast.LENGTH_SHORT).show();
                    Log.d("case1234", "onClick: Done1");
                }
            }
        });

    }
    private void uploadfile(Uri pdfUri){
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        final String filename=System.currentTimeMillis()+"";
        StorageReference storageReference=storage.getReference();

        storageReference.child(firebaseauth.getUid()).child(filename).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                DatabaseReference reference=database.getReference();
                reference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(aadhar.this,"File Uploaded Successfully",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(aadhar.this,"Failed to Upload File",Toast.LENGTH_SHORT).show();


                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(aadhar.this,"Failed to Upload File",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectpdf();
        }
        else
            Toast.makeText(aadhar.this,"Please provide permission",Toast.LENGTH_LONG).show();
    }


    private void selectpdf()
    {

        Intent intent= new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData();
            Notification.setText("File is Selected :"+data.getData().getLastPathSegment());
        }
        else
            Toast.makeText(aadhar.this,"Please select FILE",Toast.LENGTH_SHORT).show();
    }
}
