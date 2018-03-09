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

public class Signup extends AppCompatActivity
{
    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;
    @InjectView(R.id.confirm_password)
    TextView _conf_pass;

    DatabaseHelper dbh=new DatabaseHelper(this);
    Department d=new Department();

    String name,email,password,cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                trySignup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(Signup.this,login.class);
                startActivity(i);
            }
        });
    }

    void trySignup()
    {
        name=_nameText.getText().toString().trim();
        email=_emailText.getText().toString().trim();
        password=_passwordText.getText().toString().trim();
        cpass=_conf_pass.getText().toString().trim();
        if(validate())
            signup();
        else
            onSignupFail();
    }

    void signup()
    {
        Contact c=new Contact();
        c.setName(name);
        c.setEmail(email);
        c.setPassword(password);
        dbh.insertContact(c);
        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Signup.this,login.class);
        startActivity(i);
    }

    boolean validate()
    {
        boolean flag=true;
        if(!password.equals(cpass))
        {
            flag = false;
            _conf_pass.setError("Passwords do not match.");
        }
        if(name.isEmpty())
        {
            flag=false;
            _nameText.setError("Field should not be empty.");
        }
        if(email.isEmpty())
        {
            flag=false;
            _emailText.setError("Field should not be empty.");
        }
        if(password.isEmpty())
        {
            flag=false;
            _passwordText.setError("Field should not be empty.");
        }
        if(dbh.checkDup(email))
        {
            flag=false;
            Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
        }
        if(password.length()<=4)
        {
            flag=false;
            _passwordText.setError("Password is too short.");
        }
        return flag;
    }

    void onSignupFail()
    {
        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
    }
}