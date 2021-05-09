package com.example.payit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.payit.R.color;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Payment extends AppCompatActivity {

    TextView to,cancel,transfer;
    EditText amount;
    ConstraintLayout layout;
    users_get muser;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        to = findViewById(R.id.to);
        cancel = findViewById(R.id.cancel);
        transfer = findViewById(R.id.transfer);
        layout = findViewById(R.id.constraintLayout3);
        amount = findViewById(R.id.amount);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
        time=simpleDateFormat.format(calendar.getTime());


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Selected");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                to.setText(snapshot.child("name").getValue().toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if(amount.getText().equals("")){

                }

                else {


                    cancel.setBackground(getDrawable(R.drawable.btn1));


                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("K");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            int k = snapshot.child("value").getValue(Integer.TYPE);
                            k = k + 1;


                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Transaction").child(String.valueOf(k));
                            HashMap<String, Object> hashMap = new HashMap<>();

                            hashMap.put("from", "KUSHAGRA");
                            hashMap.put("to", to.getText());
                            hashMap.put("done", "NO");
                            hashMap.put("amount", amount.getText().toString());
                            hashMap.put("time", time);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {

                                }
                            });

                            HashMap<String, Integer> hashMap1 = new HashMap<>();
                            hashMap1.put("value", k);
                            ref.setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {


                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }




                Thread thread = new Thread(){
                    public void run(){

                        try{
                            sleep(1000);
                        }

                        catch (Exception e){
                            e.printStackTrace();
                        }
                        finally {
                            Intent intent = new Intent(Payment.this,Simple1.class);
                            startActivity(intent);
                        }



                    }





                };thread.start();


            }
        });

        String str = amount.getText().toString();

        transfer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                String st = amount.getText().toString();

                if(TextUtils.isEmpty(st)){
                    Toast.makeText(Payment.this,"Enter Amount First",Toast.LENGTH_SHORT).show();
                }

                else {


                    transfer.setBackground(getDrawable(R.drawable.btn2));


                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("K");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            int k = snapshot.child("value").getValue(Integer.TYPE);
                            k = k + 1;


                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Transaction").child(String.valueOf(k));
                            HashMap<String, Object> hashMap = new HashMap<>();

                            hashMap.put("from", "KUSHAGRA");
                            hashMap.put("to", to.getText());
                            hashMap.put("done", "YES");
                            hashMap.put("amount", amount.getText().toString());
                            hashMap.put("time", time);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {

                                }
                            });

                            HashMap<String, Integer> hashMap1 = new HashMap<>();
                            hashMap1.put("value", k);
                            ref.setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {


                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }





                ////




                    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Amounts").child(to.getText().toString());
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                           int num = snapshot.child("amount").getValue(Integer.TYPE);
                           int num1 = Integer.parseInt(amount.getText().toString());

                           DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Amounts").child("KUSHAGRA");

                           ref.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                   int num2 = snapshot.child("amount").getValue(Integer.TYPE);

                                   if(num2<num1){
                                       Toast.makeText(Payment.this,"Insufficient Balance",Toast.LENGTH_SHORT).show();
                                   }
                                   else {
                                       num2 = num2 - num1;

                                       HashMap<String, Integer> hashMap = new HashMap<>();
                                       hashMap.put("amount", num2);

                                       ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull @NotNull Task<Void> task) {

                                           }
                                       });

                                   }

                               }

                               @Override
                               public void onCancelled(@NonNull @NotNull DatabaseError error) {

                               }
                           });

                           num= num +num1;



                           HashMap<String,Integer> hashMap = new HashMap<>();
                           hashMap.put("amount",num);

                           database.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull @NotNull Task<Void> task) {
                                   startActivity(new Intent(Payment.this,Simple2.class));
                               }
                           });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



            }
        });


    }
}