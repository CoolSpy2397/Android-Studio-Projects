package com.intern.abhay.bingewatch;

import java.io.Serializable;

class info1 implements Serializable
{
    private String title;
    private String poster_path;
    private String date;
    private String overview;
    private Double vote;
    private String lang;

    info1()
    {    }

    String getPoster_path()
    {
        return poster_path;
    }

    public String getTitle()
    {
        return title;
    }

    void setPoster_path(String poster_path)
    {
        this.poster_path = poster_path;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    String getDate()
    {
        return date;
    }

    void setDate(String date)
    {
        this.date = date;
    }

    String getOverview()
    {
        return overview;
    }

    void setOverview(String overview)
    {
        this.overview = overview;
    }

    Double getVote()
    {
        return vote;
    }

    void setVote(Double vote)
    {
        this.vote = vote;
    }

    String getLang()
    {
        return lang;
    }

    void setLang(String lang)
    {
        this.lang=lang;
    }
}