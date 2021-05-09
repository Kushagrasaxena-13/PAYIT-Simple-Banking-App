package com.example.payit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Simple1 extends AppCompatActivity {

    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple1);
        cancel = findViewById(R.id.cancel);



        Thread thread = new Thread(){

            public void run(){
                try {
                    sleep(1000);

                }
                catch (Exception e){
                    e.printStackTrace();
                }

                finally {

                    startActivity(new Intent(Simple1.this,MainActivity.class));


                }




            }




        };thread.start();



    }
}