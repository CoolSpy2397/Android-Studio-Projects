package com.intern.abhay.bingewatch;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class explore extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        ViewPager myview=findViewById(R.id.pager);
        fragmentAdapter myadapter= new fragmentAdapter(getSupportFragmentManager());
        myview.setAdapter(myadapter);
    }
}