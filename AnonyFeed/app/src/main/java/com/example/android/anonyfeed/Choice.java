package com.example.android.anonyfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Choice extends AppCompatActivity
{

    @InjectView(R.id.btn_login)
    Button _login;
    @InjectView(R.id.btn_signup)
    Button _signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        ButterKnife.inject(this);

        _login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(Choice.this,login.class);
                startActivity(i);
            }
        });

        _signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(Choice.this,Signup.class);
                startActivity(i);
            }
        });
    }
}
