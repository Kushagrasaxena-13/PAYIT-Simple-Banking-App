package com.example.payit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText name,amount;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        amount = findViewById(R.id.amount);
        add = findViewById(R.id.add);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = name.getText().toString().toUpperCase();
                String str_amount = amount.getText().toString();

                int num = Integer.parseInt(str_amount);

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Amounts").child(str_name);
                HashMap<String,Integer> hasmap1 = new HashMap<>();

                hasmap1.put("amount",num);
                reference1.setValue(hasmap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(str_name);

                HashMap<String,Object> hashmap = new HashMap<>();

                hashmap.put("name",str_name);
                hashmap.put("amount",str_amount);

                ref.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                });


            }
        });




    }
}