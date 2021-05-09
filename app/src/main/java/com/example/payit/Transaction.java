package com.example.payit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Transaction extends AppCompatActivity {

    RecyclerView recyclerView;

    private histroy_adapter histroy_adapter;
    private List<histroy> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Transaction.this));

        mUsers = new ArrayList<>();
        histroy_adapter = new histroy_adapter(Transaction.this, mUsers);
        recyclerView.setAdapter(histroy_adapter);
        readusers();
    }


    private  void readusers(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Transaction");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mUsers.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){


                        histroy user = snapshot1.getValue(histroy.class);
                        mUsers.add(user);

                }

                histroy_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}