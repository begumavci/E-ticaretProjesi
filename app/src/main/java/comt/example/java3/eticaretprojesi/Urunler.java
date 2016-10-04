package comt.example.java3.eticaretprojesi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class Urunler extends AppCompatActivity {
    ListView list;
   static ArrayList<Urun> ls = new ArrayList<>();
    SharedPreferences sha;
    SharedPreferences.Editor edit;
    ArrayList<String> urun = new ArrayList<>();
    static JSONArray arr=null;


static int tiklanan1 =-1;
    static String tiklanan="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunler);
        sha = getSharedPreferences("xmlDosya", MODE_PRIVATE);
        edit = sha.edit();

       // String altid = getIntent().getExtras().getString("detayiistenenurundetayi");


        list = (ListView) findViewById(R.id.listView3);
        String url ="http://jsonbulut.com/json/product.php?ref=7b7392076900968d8e4ad78351ad55d3&start=0&count=100&categoryId="+tiklanan+"";
        new girisYap(url, this).execute();

        urunAdapter adp = new urunAdapter(Urunler.this, ls);
        list.setAdapter(adp);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tiklanan1=i;
                Urunler.tiklanan=ls.get(i).getProductId();
                Intent ii= new Intent(Urunler.this,UrunDetay.class);
                startActivity(ii);
            }
        });
    }

    // json sunucu ziyareti gerçekleşiyor
    class girisYap extends AsyncTask<Void,Void,Void> {

        private ProgressDialog pro;
        String data = "";
        String url = "";

        public girisYap(String url, Activity ac) {
            this.url = url;
            pro = new ProgressDialog(ac);
            pro.setMessage("Lütfen Bekleyiniz !");
            pro.show();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... param) {
            try {
                data = Jsoup.connect(url).ignoreContentType(true).execute().body();
            } catch (Exception ex) {
                Log.d("Json Hatası ", ex.toString());
            } finally {
                pro.dismiss();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            ls.clear();
            try {
                //String gele=getIntent().getExtras().getString("gel");
                /*JSONObject obj = new JSONObject(data);
                JSONArray ar = obj.getJSONArray("Products");
                JSONObject oj = ar.getJSONObject(0);
                final JSONArray ur = oj.getJSONArray("bilgiler");
                for (int i = 0; i < ur.length(); i++) {
                    JSONObject ob = ur.getJSONObject(i);
                    String productname = ob.getString("productName");
                    String description = ob.getString("description");
                    String productId = ob.getString("productId");
                    String brief = ob.getString("brief");
                    String price = ob.getString("price");
                    String resim = ob.getString("images");*/

                JSONObject ab=new JSONObject(data);
                Urunler.arr=ab.getJSONArray("Products").getJSONObject(0).getJSONArray("bilgiler");
                for(int i=0;i<Urunler.arr.length();i++){
                    JSONObject ob=Urunler.arr.getJSONObject(i);

                    String productname = ob.getString("productName");
                    urun.add(ob.getString("productName"));
                    Log.d("ürün başlık", productname);
                    urun.add(productname);
                    Urun uoz = new Urun(ob.getString("productName"), ob.getString("description"), ob.getString("productId"), ob.getString("brief"), ob.getString("price"), ob.getString("images"));
                    uoz.setProductId(ob.getString("productId"));
                    uoz.setBaslik(ob.getString("productName"));
                    uoz.setAciklama(ob.getString("brief"));
                    uoz.setDetay(ob.getString("description"));
                    uoz.setFiyat(ob.getString("price"));

                    if(ob.getBoolean("image")) {
                        String rurl = ob.getJSONArray("images").getJSONObject(0).getString("normal");
                        uoz.setResimUrl(rurl);
                    }else {
                        uoz.setResimUrl("http://www.resimkursu.com.tr/wp-content/uploads/2016/03/resim-kursu.jpg");
                    }

                    ls.add(uoz);



                   // JSONArray pp = ob.getJSONArray("categories");
                   // JSONObject dt = ur.getJSONObject(i);


                }
                //ArrayAdapter<String> adp = new ArrayAdapter<>(Urunler.this, R.layout.row, R.id.txtBaslik,urun);
                urunAdapter apt = new urunAdapter(Urunler.this,ls);
                list.setAdapter(apt);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    }

