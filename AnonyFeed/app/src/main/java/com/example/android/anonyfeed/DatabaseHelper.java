package com.example.android.anonyfeed;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="AnonyFeed.db";
    private static final String COLUMN_ID="ID";
    private static final String COLUMN_NAME="Name";
    private static final String COLUMN_EMAIL="Email";
    private static final String COLUMN_PASSWORD="Password";
    private static final String COLUMN_DEPTID="DepartmentID";
    private static final String COLUMN_DEPTNAME="DepartmentName";
    private static final String COLUMN_CONTENT="Content";
    private static final String COLUMN_REVID="ReviewID";

    private SQLiteDatabase db;

    private static final String SIGNUP_CREATE="create table Signup("+COLUMN_ID+
            " integer primary key,"+COLUMN_NAME+" text not null,"
            +COLUMN_EMAIL+" text unique not null,"+COLUMN_PASSWORD+" text not null)";

    private static final String STUDENT_CREATE="create table Student("+COLUMN_ID+
        " integer primary key,"+COLUMN_NAME+" text not null,"
        +COLUMN_EMAIL+" text unique not null,"+COLUMN_DEPTID+" integer not null";

    private static final String TEACHER_CREATE="create table Teacher("+COLUMN_ID+
            " integer primary key,"+COLUMN_NAME+" text not null,"
            +COLUMN_EMAIL+" text unique not null,"+COLUMN_DEPTID+" integer not null";

    private static final String DEPARTMENT_CREATE="create table Department("+COLUMN_DEPTID+
            " integer primary key,"+COLUMN_DEPTNAME+" text not null unique)";

    private static final String REVIEW_CREATE="create table Review("+COLUMN_REVID+" integer primary key,"+COLUMN_DEPTID+
            " integer not null,"+COLUMN_ID+" integer not null,"+COLUMN_CONTENT+" text not null)";

    private final Context fContext;

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        fContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SIGNUP_CREATE);
        db.execSQL(STUDENT_CREATE);
        db.execSQL(TEACHER_CREATE);
        db.execSQL(DEPARTMENT_CREATE);
        db.execSQL(REVIEW_CREATE);
        ContentValues cv=new ContentValues();
        Resources res =fContext.getResources();
        String[] _mytable_Records = res.getStringArray(R.array.department);
        int _Length = _mytable_Records .length;

        for(int i=1;i<=_Length;i++)
        {
            cv.put(COLUMN_DEPTID,i);
            cv.put(COLUMN_DEPTNAME,_mytable_Records[i-1]);
            db.insert("Database",null,cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        String query1="drop table if exists Signup;";
        db.execSQL(query1);
        String query2="drop table if exists Student;";
        db.execSQL(query2);
        String query3="drop table if exists Teacher;";
        db.execSQL(query3);
        String query4="drop table if exists Department;";
        db.execSQL(query4);
        String query5="drop table if exists Signup;";
        db.execSQL(query5);
        this.onCreate(db);
    }

    //Signup
    public void insertContact(Contact c)
    {
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String query="select * from Signup;";
        Cursor cur=db.rawQuery(query,null);
        int count=cur.getCount();
        cv.put(COLUMN_ID,count);
        cv.put(COLUMN_NAME,c.getName());
        cv.put(COLUMN_EMAIL,c.getEmail());
        cv.put(COLUMN_PASSWORD,c.getPassword());
        db.insert("Signup",null,cv);
        cur.close();
        db.close();
    }

    public String searchPass(String email)
    {
        String query,pass,em;
        db=this.getReadableDatabase();
        query="select "+COLUMN_EMAIL+","+COLUMN_PASSWORD+" from Signup";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getString(0);
                if(em.equals(email))
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }

    public String searchName(String email)
    {
        String query,pass,em;
        db=this.getReadableDatabase();
        query="select email,name from Signup";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getString(0);
                if(em.equals(email))
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }

    public boolean checkDup(String email)
    {
        boolean flag=false;
        String query,em;
        db=this.getReadableDatabase();
        query="select email from Signup";
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst())
        {
            do
            {
                em=c.getString(0);
                if(em.equals(email))
                {
                    flag=true;
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return flag;
    }


    //Student
    public void insertStudent(Student s)
    {
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String query="Select * from Student";
        Cursor cur=db.rawQuery(query,null);
        int count=cur.getCount();
        cv.put(COLUMN_ID,count);
        cv.put(COLUMN_NAME,s.getName());
        cv.put(COLUMN_EMAIL,s.getEmail());
        cv.put(COLUMN_DEPTID,s.getDeptID());
        db.insert("Student",null,cv);
        cur.close();
        db.close();
    }

    public String getStudName(int i)
    {
        String query,pass;
        int em;
        db=this.getReadableDatabase();
        query="select "+COLUMN_ID+","+COLUMN_NAME+" from Student";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getInt(0);
                if(i==em)
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }

    public String getStudEmail(String name)
    {
        String query,pass,em;
        db=this.getReadableDatabase();
        query="select "+COLUMN_NAME+","+COLUMN_EMAIL+" from Student";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getString(0);
                if(name.equals(em))
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }


    //Teacher
    public void insertTeacher(Teacher t)
    {
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String query="select * from Teacher";
        Cursor cur=db.rawQuery(query,null);
        int count=cur.getCount();
        cv.put(COLUMN_ID,count);
        cv.put(COLUMN_NAME,t.getName());
        cv.put(COLUMN_EMAIL,t.getEmail());
        cv.put(COLUMN_DEPTID,t.getDeptID());
        db.insert("Teacher",null,cv);
        cur.close();
        db.close();
    }

    public String getTeacherName(int i)
    {
        String query,pass;
        int em;
        db=this.getReadableDatabase();
        query="select "+COLUMN_ID+","+COLUMN_NAME+" from Teacher";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getInt(0);
                if(i==em)
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }

    public String getTeaEmail(String name)
    {
        String query,pass,em;
        db=this.getReadableDatabase();
        query="select "+COLUMN_NAME+","+COLUMN_EMAIL+" from Teacher";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getString(0);
                if(name.equals(em))
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }





    //department
    public String getDeptName(int n)
    {
        String query,pass;
        int em;
        db=this.getReadableDatabase();
        query="select "+COLUMN_DEPTID+","+COLUMN_DEPTNAME+" from Department";
        Cursor c=db.rawQuery(query,null);
        pass="##NOT FOUND##";
        if(c.moveToFirst())
        {
            do
            {
                em=c.getInt(0);
                if(n==em)
                {
                    pass=c.getString(1);
                    break;
                }
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return pass;
    }

    public int getDeptID(String n)
    {
        String query, em;
        int id;
        db = this.getReadableDatabase();
        query = "select " + COLUMN_DEPTID + "," + COLUMN_DEPTNAME + " from Department";
        Cursor c = db.rawQuery(query, null);
        id = -1;
        if (c.moveToFirst())
        {
            do
            {
                em = c.getString(1);
                if (em.equals(n))
                {
                    id= c.getInt(0);
                    break;
                }
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return id;
    }
}