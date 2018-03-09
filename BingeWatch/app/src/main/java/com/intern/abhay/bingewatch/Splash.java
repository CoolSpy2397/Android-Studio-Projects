package com.intern.abhay.bingewatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent s=new Intent(getApplicationContext(),explore.class);
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                startActivity(s);
                finish();
            }
        }).start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}