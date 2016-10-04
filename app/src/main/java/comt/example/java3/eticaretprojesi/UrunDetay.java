package comt.example.java3.eticaretprojesi;

import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class UrunDetay extends AppCompatActivity {

//static int toplam =0;
    static  String baslik,resimUrl,fiyat,aciklama,detay,id;
TextView baslik1,aciklama1,detay1,fiyat1;
    String gelen="";
    String giden="";
    String id1="";
    ImageView resim1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);
        Urun ur =  Urunler.ls.get(Urunler.tiklanan1);
        Log.d("getBaslik", ur.getBaslik());
        Log.d("getDetay", ur.getDetay());
        Log.d("getResimUrl", ur.getResimUrl());
        Log.d("getAciklama", ur.getAciklama());
        Log.d("getFiyat", ur.getFiyat());
        baslik1=(TextView) findViewById( R.id.baslik) ;
        aciklama1=(TextView) findViewById( R.id.aciklama) ;
//resim1=(ImageView) findViewById(R.id.resim);
        detay1=(TextView) findViewById( R.id.detay) ;
        fiyat1=(TextView) findViewById( R.id.fiyat) ;

      baslik=ur.getBaslik();
        detay=ur.getDetay();
        resimUrl=ur.getResimUrl();
        aciklama=ur.getAciklama();
        fiyat=ur.getFiyat();
id=ur.getProductId();

baslik1.setText(baslik);
        aciklama1.setText(aciklama);
        //resim1.setImageResource();
        detay1.setText(detay);
        fiyat1.setText(fiyat);




    }


    public void fncCikis(View v){
        finish();
        Intent i=new Intent(UrunDetay.this,Giris.class);
        startActivity(i);
    }
    public void fncSepet(View v){


                Intent ii=new Intent(UrunDetay.this,sepet.class);
                gelen=baslik.toString();
        giden=fiyat.toString();
        id1=id.toString();
//toplam+=Integer.valueOf(giden);
        ii.putExtra("giden",giden);
                ii.putExtra("gelen",gelen);

                startActivity(ii);




    }

}


