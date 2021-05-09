package com.example.payit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class histroy_adapter extends RecyclerView.Adapter<histroy_adapter.viewholder>{


    private Context mContext;
    private List<histroy> mUsers;

    private Users user1;

    RecyclerView recyclerView;

    public histroy_adapter(Context mContext,List<histroy> mUsers){
        this.mContext= mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @NotNull
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.histroy_list, parent,false);

        return new histroy_adapter.viewholder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {

        final histroy user = mUsers.get(position);

        holder.name.setText(user.getTo());
        holder.amount.setText("$ " +user.getAmount());
        holder.time.setText(user.getTime());

        if(user.getDone().equals("YES")){
            holder.request.setBackground(mContext.getDrawable(R.drawable.check));
            holder.text.setText("Successful ");
//            holder.text.setTextColor(0x1FC198);
        }
        else{
            holder.request.setBackground(mContext.getDrawable(R.drawable.cancel));
            holder.text.setText("Cancelled ");
//            holder.text.setTextColor(0xDD5158);
        }



    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView time;
        public TextView amount;
        public TextView transfer;
        public TextView request;
        public TextView text;

        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            amount = itemView.findViewById(R.id.amount);
            transfer = itemView.findViewById(R.id.transfer);
            request = itemView.findViewById(R.id.request);
            text = itemView.findViewById(R.id.text);

        }
    }
}
