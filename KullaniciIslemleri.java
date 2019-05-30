package com.example.monster.kalogram2;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.monster.kalogram2.Veritabani.COL1;
import static com.example.monster.kalogram2.Veritabani.COL2;
import static com.example.monster.kalogram2.Veritabani.COL3;
import static com.example.monster.kalogram2.Veritabani.COL4;
import static com.example.monster.kalogram2.Veritabani.COL5;
import static com.example.monster.kalogram2.Veritabani.COL6;
import static com.example.monster.kalogram2.Veritabani.COL7;
import static com.example.monster.kalogram2.Veritabani.COL8;

public class KullaniciIslemleri extends AppCompatActivity {

    Context context=this;
    EditText etAdSoyad,etYas,etBoy,etKilo,etHastalik;
    RadioButton rbKadin,rbErkek,rbEvet,rbBazen,rbHayir;
    RadioGroup RgSpor,RgCinsiyet;
    Button btnKaydet;


    String adSoyad,yas,boy,kilo,hastalik,cinsiyet,spor;
    Veritabani veritabani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_islemleri);
        veritabani=new Veritabani(this);


        etAdSoyad=findViewById(R.id.et_adSoyad);
        etYas=findViewById(R.id.et_yas);
        etBoy=findViewById(R.id.et_boy);
        etKilo=findViewById(R.id.et_kilo);
        etHastalik=findViewById(R.id.et_hastalik);
        btnKaydet=findViewById(R.id.btn_kaydet);
        RgSpor=findViewById(R.id.Rg_Spor);
        RgCinsiyet=findViewById(R.id.Rg_Cinsiyet);
        rbKadin=findViewById(R.id.rb_kadin);
        rbErkek=findViewById(R.id.rb_erkek);
        rbBazen=findViewById(R.id.rb_bazen);
        rbEvet=findViewById(R.id.rb_evet);
        rbHayir=findViewById(R.id.rb_hayır);


        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veritabani.getWritableDatabase();
                ContentValues contentValues=new ContentValues();



                adSoyad=etAdSoyad.getText().toString();
                yas=etYas.getText().toString();
                boy=etBoy.getText().toString();
                kilo=etKilo.getText().toString();
                hastalik=etHastalik.getText().toString();




             switch (RgCinsiyet.getCheckedRadioButtonId()){
                 case R.id.rb_erkek:
                     cinsiyet="erkek";
                     break;
                 case R.id.rb_kadin:
                     cinsiyet="kadin";
                     break;
                 default:
                     AlertDialog.Builder alert= new AlertDialog.Builder(context);
                     alert.setTitle("Cinsiyet seçimi yapılmadı!").setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     }).create().show();

             }

             switch (RgSpor.getCheckedRadioButtonId()){
                 case R.id.rb_evet:
                     spor="evet";
                     break;
                 case R.id.rb_bazen:
                     spor="bazen";
                     break;
                 case R.id.rb_hayır:
                     spor="hayır";
                     break;
                 default:
                     AlertDialog.Builder alert= new AlertDialog.Builder(context);
                     alert.setTitle("Spor yapıyor musunuz sorusu cevaplanmadı!").setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     }).create().show();
             }
            veritabani.insertData(adSoyad,yas,cinsiyet,boy,kilo,hastalik,spor);



                Intent i=new Intent(getApplicationContext(),MainActivity.class);
             startActivity(i);
             finish();






            }





        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){


            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}