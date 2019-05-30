package com.example.monster.kalogram2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class yemek_gecmisi extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "AILE.DB";
    public final static String TABLE_NAME = "YEMEKLER";
    public final static String COL1 = "ID";
    public final static String COL2 = "CORBALAR";
    public final static String COL3 = "SEBZE_YEMEKLERI";
    public final static String COL4 = "ET_YEMEKLERI";
    public final static String COL5 = "DENIZ_URUNLERI";
    public final static String COL6 = "SUTVESUTURUNLERI";
    public final static String COL7 = "UNLU_MAMULLER";
    public final static String COL8 = "SALATALAR";
    public final static String COL9 = "TATLILAR";
    public final static String COL10 = "TAHILLAR";
    public final static String COL11 = "KAHVALTILIKLAR";
    public final static String COL12 = "KURUYEMISLER";
    public final static String COL13 = "MEYVELER";
    public final static String COL14 = "ICECEKLER";
    public final static String COL15 = "KULLANICI";
    public final static String COL16 = "KALORI";
    public final static String COL17 = "SU";
    public final static String COL18 = "TARIH";


    public yemek_gecmisi(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , CORBALAR STRING , " +
                "SEBZE_YEMKLERI STRING , ET_YEMEKLERİ STRING,DENIZ_URUNLERI STRING , SUTVESUTURUNLERI STRING," +
                " UNLU_MAMULLER STRING, SALATALAR STRING,TATLILAR STRING, TAHILLAR STRING,KAHVALTILIKLAR STRING, KURUYEMISLER STRING," +
                "MEYVELER STRING, ICECEKLER STRING, KULLANICI STRING, KALORI STRING, SU STRING, TARIH STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }

    public boolean insertData2(String dCorbalar, String dSebzeYemekleri, String dEtYemekleri,
                              String dDenizUrunleri, String dSutveSutUrunleri,
                               String dUnlu_Mamuller, String dSalatalar,
                              String dTatlilar, String dTahillar,
                               String dKahvaltiliklar, String dKuruYemisler,
                              String dMeyveler, String dIcecekler,
                               String dKullanıcı,String dKalori,String dSu,String dTarih) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,dCorbalar);
        contentValues.put(COL3,dSebzeYemekleri);
        contentValues.put(COL4,dEtYemekleri);
        contentValues.put(COL5,dDenizUrunleri);
        contentValues.put(COL6,dSutveSutUrunleri);
        contentValues.put(COL7,dUnlu_Mamuller);
        contentValues.put(COL8,dSalatalar);
        contentValues.put(COL9,dTatlilar);
        contentValues.put(COL10,dTahillar);
        contentValues.put(COL11,dKahvaltiliklar);
        contentValues.put(COL12,dKuruYemisler);
        contentValues.put(COL13,dMeyveler);
        contentValues.put(COL14,dIcecekler);
        contentValues.put(COL15,dKullanıcı);
        contentValues.put(COL16,dKalori);
        contentValues.put(COL17,dSu);
        contentValues.put(COL18,dTarih);

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

    public boolean UpdateDataK(String eKalorim, String nKalorim){

        SQLiteDatabase db2=this.getWritableDatabase();
        ContentValues contentValue=new ContentValues();
        contentValue.put(COL16,nKalorim);
        db2.update(TABLE_NAME,contentValue,"KALORI=?", new String[]{String.valueOf(eKalorim)});

        return true;
    }
}