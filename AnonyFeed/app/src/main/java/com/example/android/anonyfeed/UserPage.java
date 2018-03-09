package com.example.android.anonyfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserPage extends AppCompatActivity
{
    @InjectView(R.id.welcome)
    TextView _welcome;
    @InjectView(R.id.dept_list)
    Spinner _deptList;
    @InjectView(R.id.submit)
    Button _submit;

    DatabaseHelper dbh=new DatabaseHelper(this);
    String name,email,selected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        ButterKnife.inject(this);

        email=null;
        if(savedInstanceState==null)
        {
            Bundle extras=getIntent().getExtras();
            if(extras!=null)
            {
                email=extras.getString("EmailID");
                name=dbh.searchName(email);
                _welcome.setText(name);
            }
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _deptList.setAdapter(adapter);

        _submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    submit();
            }
        });
    }

    void submit()
    {
        selected=_deptList.getSelectedItem().toString();
        if(selected.equals("Department"))
        {
            Toast.makeText(getApplicationContext(),"Select Something",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Student s = new Student();
            s.setName(name);
            s.setEmail(email);
            s.setDeptID(2);
            dbh.insertStudent(s);
            Intent i = new Intent(UserPage.this, StuFeed.class);
            startActivity(i);
        }
    }
}