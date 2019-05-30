package com.example.monster.kalogram2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Yemeklerdb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="YEMEKLER.DB" ;
    public final static String TABLE_NAME="YEMEKLER";
    public final static String COL1="YEMEKADI";
    public final static String COL2="KALORI";



    public Yemeklerdb(Context context) {
        super(context, DATABASE_NAME ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , YEMEKADI TEXT ,KALORI TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean upgrade(String dyemekadi, String dKalori){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,dyemekadi);
        contentValues.put(COL2,dKalori);
        long result=db.insert(TABLE_NAME, null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

}
