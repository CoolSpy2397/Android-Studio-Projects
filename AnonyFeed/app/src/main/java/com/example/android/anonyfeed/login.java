package com.example.android.anonyfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class login extends AppCompatActivity
{

    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;

    DatabaseHelper dbh=new DatabaseHelper(this);

    String email,password,pass,type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        _loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tryLogin();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });
    }

    void tryLogin()
    {
        email=_emailText.getText().toString().trim();
        password=_passwordText.getText().toString().trim();
        type="login";
        BackgroundWorker bw=new BackgroundWorker(this);
        bw.execute(email,password,type);
    }

    boolean validate()
    {
        boolean flag=true;
        if(!password.equals(pass))
        {
            flag=false;
        }
        if(email.isEmpty()||password.isEmpty())
        {
            flag=false;
        }
        return flag;
    }

    void Login()
    {
        Intent i=new Intent(login.this,UserPage.class);
        i.putExtra("EmailID",email);
        startActivity(i);
    }

    void onLoginFail()
    {
        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
    }
}