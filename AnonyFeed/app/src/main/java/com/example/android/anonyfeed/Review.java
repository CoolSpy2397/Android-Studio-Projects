package com.example.android.anonyfeed;

public class Review
{
    int tea_id,stud_id;
    String content;

    public void setTeaId(int r)
    {
        tea_id=r;
    }

    public int getTeaId()
    {
        return this.tea_id;
    }

    public void setStudId(int r)
    {
        stud_id=r;
    }

    public int getStudId()
    {
        return this.stud_id;
    }

    public void setContent(String c)
    {
        content=c;
    }

    public String getContent()
    {
        return this.content;
    }
}
