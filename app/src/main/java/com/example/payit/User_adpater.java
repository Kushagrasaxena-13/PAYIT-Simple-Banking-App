package com.example.payit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class User_adpater extends RecyclerView.Adapter<User_adpater.viewholder>{

    private Context mContext;
    private List<users_list> mUsers;

    private Users user1;

    RecyclerView recyclerView;

    public User_adpater(Context mContext,List<users_list> mUsers){
        this.mContext= mContext;
        this.mUsers = mUsers;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user, parent,false);

        return new User_adpater.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        final users_list user = mUsers.get(position);

//        holder.city.setText(reference.orderByChild(user.toString()).toString());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.user.setText(user.getName());

                holder.user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Selected");
                        HashMap<String , Object> hashMap = new HashMap<>();

                        hashMap.put("name",user.getName());

                        ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                mContext.startActivity(new Intent(mContext,Payment.class));
                            }
                        });
                    }

                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        public TextView user;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user);
        }
    }
}
