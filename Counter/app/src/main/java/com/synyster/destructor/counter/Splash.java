package com.synyster.destructor.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity
{
    public static long SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent intent=new Intent(Splash.this,MainActivity.class);
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(SPLASH_TIME_OUT);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                startActivity(intent);
                finish();
            }
        }).start();
    }
}

