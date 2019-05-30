package com.example.monster.kalogram2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabani extends SQLiteOpenHelper {
    public final static String DATABASE_NAME="AILE.DB";
    public final static String TABLE_NAME="UYELER";
    public final static String COL1="ID";
    public final static String COL2="ISIM_SOYISIM";
    public final static String COL3="YAS";
    public final static String COL4="CINSIYET";
    public final static String COL5="BOY";
    public final static String COL6="KILO";
    public final static String COL7="HASTALIK";
    public final static String COL8="SPOR";


    public Veritabani(Context context) {
        super(context, DATABASE_NAME ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , ISIM_SOYISIM TEXT , " +
                "YAS STRING , CINSIYET STRING,BOY STRING , KILO STRING, HASTALIK STRING, SPOR STRING )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String dIsimSoyisim, String dYas, String dCinsiyet, String dBoy, String dKilo, String dHastalik, String dSpor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,dIsimSoyisim);
        contentValues.put(COL3,dYas);
        contentValues.put(COL4,dCinsiyet);
        contentValues.put(COL5,dBoy);
        contentValues.put(COL6,dKilo);
        contentValues.put(COL7,dHastalik);
        contentValues.put(COL8,dSpor);
        long result=db.insert(TABLE_NAME, null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor VeriGoster(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT *FROM " + TABLE_NAME, null);
        return data;
    }
/*
    public void deleteData(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + " = ?", new String[] { String .valueOf(id)});

    }

    public Cursor getID(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor ID = db.rawQuery("SELECT * FROM LIST WHERE ID='"+COL1+"'", null);
        return ID;
    }
    */

}
