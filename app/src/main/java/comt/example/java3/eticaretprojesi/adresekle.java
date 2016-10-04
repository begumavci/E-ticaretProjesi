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
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class adresekle extends AppCompatActivity {
EditText il,ilce,mahalle,adres,kapi,not;
    static  String adres9;
    SharedPreferences sha;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adresekle);
il=(EditText) findViewById(R.id.editText);
        sha = getSharedPreferences("xmlDosya", MODE_PRIVATE);
        edit = sha.edit();
        ilce=(EditText) findViewById(R.id.editText2);
        mahalle=(EditText) findViewById(R.id.editText3);
        adres=(EditText) findViewById(R.id.editText4);
        kapi=(EditText) findViewById(R.id.editText5);
        not=(EditText) findViewById(R.id.editText6);
    }
    public void fncEkle(View v){
        String il1 = il.getText().toString().trim();
        String ilce1 = ilce.getText().toString().trim();
        String mahalle1 = mahalle.getText().toString().trim();
        String adres1 = adres.getText().toString().trim();
        String kapi1 = kapi.getText().toString().trim();
        String not1 = not.getText().toString().trim();
        edit.putString("adres",adres1);

        String url = "http://jsonbulut.com/json/addressAdd.php?ref=7b7392076900968d8e4ad78351ad55d3&musterilerID=35&il='"+il1+"'&ilce='"+ilce1+"'&Mahalle='"+mahalle1+"'&adres='"+adres1+"'&kapiNo='"+kapi1+"'&notBilgi='"+not1+"'";
        new adresekleme(url,this).execute();
        edit.commit();
        Intent i=new Intent(adresekle.this, comt.example.java3.eticaretprojesi.adres.class);
        startActivity(i);
    }
    public void fncGeri(View v){
        onBackPressed();
    }
    class adresekleme extends AsyncTask<Void,Void,Void> {

        private ProgressDialog pro;
        String data = "";
        String url = "";
        public adresekleme(String url, Activity ac) {
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
            }catch (Exception ex) {
                Log.d("Json Hatası ", ex.toString());
            }finally {
                pro.dismiss();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            try {
                JSONObject obj = new JSONObject(data);
                JSONArray arr = obj.getJSONArray("user");
               // JSONObject oj = arr.getJSONObject(0);
                Toast.makeText(adresekle.this, "Ekleme Başarılı", Toast.LENGTH_SHORT).show();
                onBackPressed();
             // adres9=  oj.getJSONObject("adres");
            }    catch (JSONException e) {
                e.printStackTrace();
            }

        }}
    }


