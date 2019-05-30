package com.example.monster.kalogram2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context=this;
    Button btnYemek;
    Button btnKisi;
    ListView IsımSoyisim;

    Veritabani db;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnYemek=findViewById(R.id.btn_yemek);
        btnKisi=findViewById(R.id.btn_kisi);
        IsımSoyisim=findViewById(R.id.mList1);
        db= new Veritabani(this);
        listItem= new ArrayList<>();

       VeriGoster();
       IsımSoyisim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long d) {
               String text= IsımSoyisim.getItemAtPosition(i).toString();
               Intent k=new Intent(getApplicationContext(),KisiBilgileri.class);
               k.putExtra("send_string",text);
               startActivity(k);
           }
       });


        btnKisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),KullaniciIslemleri.class);
                startActivity(i);
                finish();

            }
        });
        btnYemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Ekle.class);
                startActivity(i);
                finish();
            }
        });



    }

    private void VeriGoster() {
        Cursor cursor=db.VeriGoster();
        if (cursor.getCount()==0){
            Toast.makeText(this,"Gösterilecek kişi yok! Lütfen kişi bilgilerinizi kaydedin.",Toast.LENGTH_SHORT).show();

        }
        else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);

            IsımSoyisim.setAdapter(adapter);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
           AlertDialog.Builder alert= new AlertDialog.Builder(context);
            alert.setTitle("Çıkmak istediğinizden emin misiniz?")
            .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
                }
            }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();


        }
        return super.onKeyDown(keyCode, event);
    }



}
