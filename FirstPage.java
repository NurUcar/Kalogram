package com.example.monster.kalogram2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FirstPage extends AppCompatActivity {

    @Override

    public void onCreate(Bundle first) {
        super.onCreate(first);
        setContentView(R.layout.activity_first_page);
        Thread zamanlayici = new Thread(){
            public void run(){
                try{
                    sleep(2500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                    Log.i("tago", "Timer didn't work");

                }
                finally {
                    {
                        Intent i= new Intent(FirstPage.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        };
        zamanlayici.start();
    }
}

