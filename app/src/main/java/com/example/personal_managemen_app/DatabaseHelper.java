package com.example.personal_managemen_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Initialize all the fields needed for database
    public static final String DATABASE_NAME = "Friends.db";
    public static final String TABLE_NAME = "student_data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "LastName";
    public static final String COL_4 = "Gender";
    public static final String COL_5 = "Age";
    public static final String COL_6 = "Address";
    public static final String LBR = "(";
    public static final String RBR = ")";
    public static final String COM = ",";

    //Just pass context of the app to make it simpler
    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, 2 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating table



                db.execSQL( "create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT" + COM + COL_3 + " TEXT" + COM + COL_4 + " INTEGER" + COM + COL_5 + " INTEGER" +RBR );



    }
//
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Dropping old table
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate( db );

    }

    //Insert data in database
    public boolean instertData(String f_name, String l_name, String gender, String age,String address){

        //Get the instance of SQL Database which we have created
        SQLiteDatabase db = getWritableDatabase();

        //To pass all the values in database
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, f_name );
        contentValues.put( COL_3, l_name );
        contentValues.put( COL_4, gender );
        contentValues.put( COL_5, age );
      //  contentValues.put( COL_6, address );

        long result = db.insert( TABLE_NAME, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }

    //Cursor class is used to move around in the database
    public Cursor getData(){

        //Get the data from database
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }

    //Update fields of database using ID (Unique identifier)
    public boolean updateData(String id, String f_name, String l_name, String age, String geder,String address){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues(  );



        if(!id.isEmpty() && !f_name.isEmpty() && !l_name.isEmpty() && !age.isEmpty() && !geder.isEmpty() && !address.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, f_name );
            contentValues.put( COL_3, l_name );
            contentValues.put( COL_4, geder );
            contentValues.put( COL_5, age);
           // contentValues.put( COL_6, address);
        }

        // UPDATE query
        db.update( TABLE_NAME, contentValues, "ID = ?", new String[]{id} );
        return true;
    }

    //Delete data from the databse using ID (Primary Key)
    public Integer deleteData(String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete( TABLE_NAME, "ID = ?", new String [] {id} );
    }
}

