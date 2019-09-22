package com.example.picttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.picttp.userinfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import de.hdodenhof.circleimageview.CircleImageView;


public class page_3_1_admin extends AppCompatActivity {
    //private SearchView mySearchView;
    //private androidx.appcompat.widget.Toolbar mToolbar;

    private RecyclerView mUsersList;

    private DatabaseReference mUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_1_admin);
        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        mUsersList = (RecyclerView)findViewById(R.id.users_list);
        mUsersList.setHasFixedSize(true);
        mUsersList.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<userinfo> options=new FirebaseRecyclerOptions.Builder<userinfo>()
                .setQuery(mUsersDatabase,userinfo.class).build();

    FirebaseRecyclerAdapter<userinfo,UsersViewHolder> firebaseRecyclerAdapter =
            new FirebaseRecyclerAdapter<userinfo, UsersViewHolder>(options)
            {
                @NonNull
                @Override
                public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                    View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_users_single_layout,viewGroup,false);
                    UsersViewHolder viewHolder = new UsersViewHolder(view);
                    return viewHolder;
                }

                protected void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int position, @NonNull userinfo users) {
                    //usersViewHolder.usermail.setText(users.getEmail());
                    usersViewHolder.fullName.setText(users.getUsername());
                    //usersViewHolder.profileIv.//Image Loading Needs Picasso library

                    final String user_ID=getRef(position).getKey();
                    usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent requestintent = new Intent(page_3_1_admin.this,studentinfo.class);
                            requestintent.putExtra("user_id",user_ID);
                            startActivity(requestintent);
                        }
                    });
                }
            };

       mUsersList.setAdapter(firebaseRecyclerAdapter);
       firebaseRecyclerAdapter.startListening();
}


    public class UsersViewHolder extends RecyclerView.ViewHolder{

    View mView;
    TextView email,fullName;
    CircleImageView profileIv;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);

       // email= itemView.findViewById(R.id.users_single_email);
        fullName=itemView.findViewById(R.id.stud_name);
        profileIv=itemView.findViewById(R.id.imageView);
        mView=itemView;
    }
}
}

