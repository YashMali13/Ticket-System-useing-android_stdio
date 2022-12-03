package com.example.project_tr;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbmanager extends SQLiteOpenHelper
{
    private static final String dbname="Data";
    public dbmanager(@Nullable Context context)
    {
        super(context, dbname, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String qry="create table UserData ( id integer primary key autoincrement, name text, source text, destination text, date text, email text)";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String qry="DROP TABLE IF EXISTS UserData";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }

    public  String addrecord(String name, String source, String destination, String date, String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("source",source);
        cv.put("destination",destination);
        cv.put("date",date);
        cv.put("email",email);
        float res=db.insert("UserData",null,cv);

        if(res==-1)
        {
            return "Failed To Offer !";
        }
        else
        {
            return "You Successfully offered !";
        }

    }

    public Cursor readalldata()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        String qry="select * from UserData order by id desc";
        Cursor cursor=db.rawQuery(qry,null);
        return  cursor;

    }

}


