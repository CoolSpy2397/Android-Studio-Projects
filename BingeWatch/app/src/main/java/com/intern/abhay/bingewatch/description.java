package com.intern.abhay.bingewatch;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class description extends AppCompatActivity
{
    String lang="N/A";
    info information;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Bundle extras= getIntent().getExtras();
        assert extras != null;
        information=(info)extras.getSerializable("Value");
        TextView tv=findViewById(R.id.name);
        ImageView im=findViewById(R.id.image);
        TextView tv1=findViewById(R.id.date);
        TextView tv2=findViewById(R.id.vote);
        TextView tv3=findViewById(R.id.overview);
        TextView tv4=findViewById(R.id.viewlang);
        try
        {
            assert information != null;
            tv.setText(information.getTitle());
            tv1.setText(information.getDate());
            tv2.setText(information.getVote().toString());
            tv3.setText(information.getOverview());
            detLang();
            tv4.setText(lang);
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500/"+information.getPoster_path())
                    .into(im);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    void detLang()
    {
        String l;
        switch(l=information.getLang())
        {
            case "en": lang="English";
                break;
            case "hi": lang="Hindi";
                break;
            case "ja": lang="Japanese";
                break;
            case "it": lang="Italian";
                break;
            case "pt": lang="Portuguese";
                break;
            case "es": lang="Spanish";
                break;
            case "fr": lang="French";
                break;
            default: lang=l;
                break;
        }
    }
}