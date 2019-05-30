package com.example.monster.kalogram2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Scanner;

public class KisiBilgileri extends AppCompatActivity {
    TextView Baslik;
    TextView Cesit;
    Spinner mSpinner;
    ListView yemeklist;
    TextView ToplamKalori;
    TextView ToplamSu;
    Button btnInfo;
    Button btnSifirla;
    String DbCesit;
    String value;
    SharedPreferences preferences;
    yemek_gecmisi mDb;
    String CORBALAR="null";
    String SEBZE_YEMEKLERI="null";
    String ET_YEMEKLERI="null";
    String DENIZ_URUNLERI="null";
    String SUTVESUTURUNLERI="null";
    String UNLU_MAMULLER="null";
    String SALATALAR="null";
    String TATLILAR="null";
    String TAHILLAR="null";
    String KAHVALTILIKLAR="null";
    String KURUYEMİSLER="null";
    String MEYVELER="null";
    String ICECEKLER="null";
    String KULLANICI="null";
    String KALORİ="0";
    String SU="0";
    Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_bilgileri);
        Baslik=findViewById(R.id.adSoyad);
        mSpinner=findViewById(R.id.spinner);
        Cesit=findViewById(R.id.cesit);
        yemeklist=findViewById(R.id.yemek_list);
        ToplamKalori= findViewById(R.id.Toplam_Kalori);
        btnSifirla=findViewById(R.id.sifirla);
        ToplamSu=findViewById(R.id.Su);
        btnInfo=findViewById(R.id.info);
        ToplamKalori.setText("0");
        ToplamSu.setText("0");
        TextView date =findViewById(R.id.textView3);


        mDb= new yemek_gecmisi(this);

        Bundle extra= getIntent().getExtras();
        value=extra.getString("send_string");
        Baslik.setText(value);


        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,
                R.array.yiyecek_icecek,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] Liste=getResources().getStringArray(R.array.yiyecek_icecek);
                if (position!=0){
                    DbCesit=Liste[position];
                    Cesit.setText(DbCesit);
                    Cesit.setVisibility(View.VISIBLE);
                    open_json(Liste[position]);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto-generated method stub
            }
        });

        //VeriGoster();




        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Tuketim_Gecmisi.class);
                startActivity(intent);
            }
        });
       myCalendar = Calendar.getInstance();
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int gun=myCalendar.get(Calendar.DAY_OF_MONTH);
        int ay=myCalendar.get(Calendar.MONTH);
        int yil= myCalendar.get(Calendar.YEAR);
       // date.setText(String.valueOf(gun)+String.valueOf(ay)+String.valueOf(yil));


        if (hour==0||hour==24){
            ToplamSu.setText("0");
            ToplamKalori.setText("0");

        }


    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("kalorim",ToplamKalori.getText().toString());
        editor.putString("suyum",ToplamSu.getText().toString());
        editor.commit();
        super.onPause();
    }


    public void open_json(String deger){
        Resources res =getResources();
        InputStream is= res.openRawResource(R.raw.mdata);
        Scanner scanner= new Scanner(is);
        StringBuilder builder= new StringBuilder();
        while(scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString(),deger);
    }

    private void parseJson(String json, String deger2) {
        StringBuilder builder= new StringBuilder();
        try {
            JSONObject root = new JSONObject(json);
            JSONArray yemek=root.getJSONArray(deger2);

            for(int i=0; i<yemek.length(); i++){
                JSONObject cesit=yemek.getJSONObject(i);
                builder.append(cesit.getString("isim")).append("/")
                        .append(cesit.getString("kalori")).append("-")
                        .append(cesit.getString("miktar")).append("/");
                String mString=builder.toString();
                String[] StringArray = mString.split("/");
                double vl;
                if(StringArray.length%2==0)
                    vl=StringArray.length/2;
                else {
                    vl = StringArray.length / 2;
                    vl=vl+0.5;
                }

                String[] isimArray= new String[(int)vl];
                int value=0;

                StringBuilder stryapici = new StringBuilder();
                String mString2;
                for (int k=0; k<StringArray.length;k++){
                    if (k%2==0){
                        isimArray[value]=StringArray[k];
                        value++;

                    }
                    else{
                        stryapici.append(StringArray[k]).append("-");


                    }
                }


                mString2=stryapici.toString();
                String[] StringArray2 = mString2.split("-");
                double vl2;
                int value2=0;
                int value3=0;
                if(StringArray2.length%2==0)
                    vl2=StringArray2.length/2;
                else {
                    vl2 = StringArray2.length / 2;
                    vl2=vl2+0.5;
                }

                final String[] kaloriArray= new String[(int)vl2];

                final String[] miktarArray= new String[(int)vl2];


                for (int m=0; m<StringArray2.length;m++){

                    if (m%2==0){
                        kaloriArray[value2]=StringArray2[m];
                        value2++;

                    }
                    else{
                        miktarArray[value3]=StringArray2[m];
                        value3++;


                    }
                }




                ArrayAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,isimArray );
                yemeklist.setAdapter(adapter);
                yemeklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int kalori2=Integer.parseInt(kaloriArray[position]);
                        int toplam=Integer.parseInt(ToplamKalori.getText().toString());
                        toplam=toplam+kalori2;
                       // Boolean result=mDb.UpdateDataK(ToplamKalori.getText().toString(),String.valueOf(toplam));
                      //  if(result==true)
                           // Toast.makeText(getApplicationContext(),"calıstı",Toast.LENGTH_SHORT).show();

                        ToplamKalori.setText(String.valueOf(toplam));
                        String val =yemeklist.getItemAtPosition(position).toString();

                        if(val.equals("Su")){
                            ToplamSu.setText(String.valueOf((Integer.parseInt(ToplamSu.getText().toString())+1)*100));
                        }


                        if(DbCesit.equals("Çorbalar")) {
                            CORBALAR=val;
                        } else if(DbCesit.equals("Sebze Yemekleri")) {
                            SEBZE_YEMEKLERI=val;
                        }else if(DbCesit.equals("Et Yemekleri")) {
                            ET_YEMEKLERI=val;
                        }else if(DbCesit.equals("Deniz Ürünleri")) {
                            DENIZ_URUNLERI=val;
                        }else if(DbCesit.equals("Süt ve Süt Ürünleri")) {
                            SUTVESUTURUNLERI=val;
                        }else if(DbCesit.equals("Unlu Mamüller")) {
                            UNLU_MAMULLER=val;
                        }else if(DbCesit.equals("Salatalar")) {
                            SALATALAR=val;
                        }else if(DbCesit.equals("Tatlılar")) {
                            TATLILAR=val;
                        }else if(DbCesit.equals("Tahıllar")) {
                            TAHILLAR=val;
                        }else if(DbCesit.equals("Kahvaltılıklar")) {
                            KAHVALTILIKLAR=val;
                        }else if(DbCesit.equals("Kuruyemişler")) {
                            KURUYEMİSLER=val;
                        }else if(DbCesit.equals("Meyveler")) {
                            MEYVELER=val;
                        }else if(DbCesit.equals("İçecekler")) {
                            ICECEKLER=val;
                        }
                        KULLANICI=Baslik.getText().toString();
                           /* mDb.insertData2(CORBALAR,SEBZE_YEMEKLERI,ET_YEMEKLERI,DENIZ_URUNLERI,
                                    SUTVESUTURUNLERI,UNLU_MAMULLER,SALATALAR,TATLILAR,TAHILLAR,
                                    KAHVALTILIKLAR,KURUYEMİSLER,MEYVELER,ICECEKLER,KULLANICI);*/


                    }
                });

            }





        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

  /*  private void VeriGoster() {
        Cursor cursor=mDb.VeriGoster();
        int maxValuek=0;
        int maxValues=0;
        if (cursor.getCount()==0){

        }
        else{
            while(cursor.moveToNext()){
                int valK= Integer.parseInt(cursor.getString(15));
                int valS=Integer.parseInt(cursor.getString(16));
                if (valK>maxValuek)
                    maxValuek=valK;
                if (valS>maxValues)
                    maxValues=valS;
            }

        }
        ToplamKalori.setText(maxValuek);
        ToplamSu.setText(maxValues);
    }*/



}
