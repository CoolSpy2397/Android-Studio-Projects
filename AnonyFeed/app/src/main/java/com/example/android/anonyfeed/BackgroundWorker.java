package com.example.android.anonyfeed;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class BackgroundWorker extends AsyncTask<String,Void,String>
{
    Context context;
    AlertDialog a;

    BackgroundWorker(Context ctx)
    {
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String type=params[0];
        String email=params[1];
        String password=params[2];
        String login_url="http://10.160.18.167/login.php";
        if(type.equals("login"))
        {
            try
            {
                URL url=new URL(login_url);
                HttpURLConnection huc=(HttpURLConnection) url.openConnection();
                huc.setRequestMethod("POST");
                huc.setDoOutput(true);
                huc.setDoInput(true);
                OutputStream os=huc.getOutputStream();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String postdata= URLEncoder.encode("Email","UTF-8")+" = "+URLEncoder.encode(email,"UTF-8")+"\n"+
                        URLEncoder.encode("Password","UTF-8")+" = "+URLEncoder.encode(password,"UTF-8");
                bw.write(postdata);
                bw.flush();
                bw.close();
                os.close();
                InputStream is=huc.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String result="";
                String line="";
                while((line=br.readLine())!=null)
                {
                    result+=line;
                }
                br.close();
                is.close();
                huc.disconnect();
                return result;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        a=new AlertDialog.Builder(context).create();
        a.setTitle("LoginStatus");
    }

    @Override
    protected void onPostExecute(String result)
    {
        a.setMessage(result);
        a.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
