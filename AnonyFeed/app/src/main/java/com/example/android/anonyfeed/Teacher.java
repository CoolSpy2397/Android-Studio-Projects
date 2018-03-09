package com.example.android.anonyfeed;

public class Teacher
{
    int dept_id;
    String name,email;

    public void setDeptID(int n)
    {
        this.dept_id=n;
    }

    public int getDeptID()
    {
        return this.dept_id;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String n)
    {
        name=n;
    }

    public void setEmail(String e)
    {
        email=e;
    }

}
