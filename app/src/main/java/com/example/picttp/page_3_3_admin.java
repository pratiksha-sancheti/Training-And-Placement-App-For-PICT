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
import com.example.picttp.companydata;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import de.hdodenhof.circleimageview.CircleImageView;

public class page_3_3_admin extends AppCompatActivity {

    private RecyclerView mcompList;

    private DatabaseReference mUsersDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3_3_admin);
        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("company");
        mcompList = (RecyclerView)findViewById(R.id.company_list);
        mcompList.setHasFixedSize(true);
        mcompList.setLayoutManager(new LinearLayoutManager(this));
    }
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<companydata> options=new FirebaseRecyclerOptions.Builder<companydata>()
                .setQuery(mUsersDatabase,companydata.class).build();

        FirebaseRecyclerAdapter<companydata, page_3_3_admin.UsersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<companydata, page_3_3_admin.UsersViewHolder>(options)
                {
                    @NonNull
                    @Override
                    public page_3_3_admin.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_comapny_single_layout,viewGroup,false);
                        page_3_3_admin.UsersViewHolder viewHolder = new page_3_3_admin.UsersViewHolder(view);
                        return viewHolder;
                    }

                    protected void onBindViewHolder(@NonNull page_3_3_admin.UsersViewHolder usersViewHolder, int position, @NonNull companydata users) {
                        //usersViewHolder.usermail.setText(users.getEmail());
                        usersViewHolder.comp_name.setText(users.getComp_name());
                        //usersViewHolder.profileIv.//Image Loading Needs Picasso library

                        final String user_ID=getRef(position).getKey();
                        usersViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent requestintent = new Intent(page_3_3_admin.this,companyinfo.class);
                                requestintent.putExtra("user_id",user_ID);
                                startActivity(requestintent);
                            }
                        });
                    }
                };

        mcompList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView comp_name;
        CircleImageView profileIv;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            // email= itemView.findViewById(R.id.users_single_email);
            comp_name=itemView.findViewById(R.id.comp_name);
            profileIv=itemView.findViewById(R.id.imageView);
            mView=itemView;
        }
    }

}
