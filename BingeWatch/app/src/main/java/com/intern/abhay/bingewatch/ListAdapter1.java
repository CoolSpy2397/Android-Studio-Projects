package com.intern.abhay.bingewatch;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

class ListAdapter1 extends ArrayAdapter<info1>
{
    private ArrayList<info1> data;

    ListAdapter1(Context context, ArrayList<info1> information)
    {
        super(context,R.layout.customlist,information);
        data=information;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View customView=convertView;
        ViewHolder holder=new ViewHolder();
        if(customView==null)
        {
            LayoutInflater inf = LayoutInflater.from(getContext());
            customView = inf.inflate(R.layout.customlist, parent, false);
            holder.name=(TextView)customView.findViewById(R.id.name);
            holder.poster_path=(ImageView)customView.findViewById((R.id.image));
            customView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder)customView.getTag();
        }
        holder.name.setText(data.get(position).getTitle());
        Glide.with(getContext())
                .load("http://image.tmdb.org/t/p/w500/"+data.get(position).getPoster_path())
                .into(holder.poster_path);
        return customView;
    }

    private static class ViewHolder
    {
        public TextView name;
        ImageView poster_path;
    }
}