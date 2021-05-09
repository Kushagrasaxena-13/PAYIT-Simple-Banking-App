package com.example.payit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Simple2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple2);


        Thread thread = new Thread(){
            public void run(){

                try{
                    sleep(3000);
                }

                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Simple2.this,MainActivity.class);
                    startActivity(intent);
                }



            }





        };thread.start();
    }
}