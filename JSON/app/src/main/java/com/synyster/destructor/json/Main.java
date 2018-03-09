package com.synyster.destructor.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class Main extends AppCompatActivity
{
    JSONObject j=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String s="{\"Name\":\"Abhay\",\n"+
            "\"Roll\":\"111\",\n"+
            "\"Subject\":{\"Enter\":[\"Phy\",\"Chem\",\"Math\"],\n"+
            "\"Passed\":\"true\"\n"+
            "}\n"+
            "}";


    String str;

    public void as(View v)
    {
        try
        {
            j= new JSONObject(s);
            str=j.getString("Name");
        }
        catch (JSONException j)
        {
            j.printStackTrace();
        }
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}