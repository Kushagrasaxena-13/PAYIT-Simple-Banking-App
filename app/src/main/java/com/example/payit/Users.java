package com.example.payit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Users extends AppCompatActivity {

    RecyclerView recyclerView;

    private User_adpater city_adapter;
    ImageView users;
    private List<users_list> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        users = findViewById(R.id.back);


        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Users.this));

        mUsers = new ArrayList<>();
        city_adapter = new User_adpater(Users.this, mUsers);
        recyclerView.setAdapter(city_adapter);
        readusers();

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Users.this,MainActivity.class));
            }
        });
    }

    private  void readusers(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mUsers.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    if(snapshot1.getValue(users_list.class).getName().equals("KUSHAGRA")){

                    }
                    else {
                        users_list user = snapshot1.getValue(users_list.class);
                        mUsers.add(user);
                    }
                }

                city_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}