package com.example.android.anonyfeed;

public class Student
{
    private int dept_id;
    private String name,email;

    public void setDeptID(int n)
    {
        this.dept_id=n;
    }

    public int getDeptID()
    {
        return this.dept_id;
    }

    public void setName(String n)
    {
        name=n;
    }

    public String getName()
    {
        return this.name;
    }

    public void setEmail(String n)
    {
        email=n;
    }

    public String getEmail()
    {
        return this.email;
    }
}
