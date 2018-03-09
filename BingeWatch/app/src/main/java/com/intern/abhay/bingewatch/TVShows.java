package com.intern.abhay.bingewatch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TVShows extends android.support.v4.app.Fragment
{
    GridView List;
    ListAdapter1 ladapter;
    ArrayList<info1> data=new ArrayList<>();
    info1 information;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_tvshows, container, false);
        List=view.findViewById(R.id.mylist1);
        for(int i=1;i<=10;i++)
        {
            new tvlist().execute("https://api.themoviedb.org/3/tv/popular?api_key=73d4d240eb3a575c68cbdeb31ff86836&language=en-US&page="+i);
        }
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private class tvlist extends AsyncTask<String, Void, String>
    {
        HttpURLConnection conn;
        URL myurl;
        StringBuilder sb;
        String result = null;

        @Override
        protected String doInBackground(String... args)
        {
            try
            {
                myurl = new URL(args[0]);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            try
            {
                conn = (HttpURLConnection) myurl.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                sb = new StringBuilder();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line);
                }
                reader.close();
                is.close();
                result = sb.toString();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            try
            {
                JSONObject jobj= new JSONObject(result);
                JSONArray jarray=jobj.getJSONArray("results");
                for(int i=0;i<jarray.length();i++)
                {
                    JSONObject realobj= jarray.getJSONObject(i);
                    information=new info1();
                    information.setTitle(realobj.getString("original_name"));
                    information.setPoster_path(realobj.getString("poster_path"));
                    information.setDate(realobj.getString("first_air_date"));
                    information.setOverview(realobj.getString("overview"));
                    information.setVote(realobj.getDouble("vote_average"));
                    information.setLang(realobj.getString("original_language"));
                    data.add(information);
                }
                ladapter=new ListAdapter1(getContext(),data);
                List.setAdapter(ladapter);
                List.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Intent s=new Intent(getContext(),description1.class);
                        info1 im=(info1)adapterView.getItemAtPosition(i);
                        s.putExtra("Value",im);
                        startActivity(s);
                    }
                });
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}