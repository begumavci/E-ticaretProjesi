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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;

public class adres extends AppCompatActivity {
ListView ls;
    SharedPreferences sha;
    SharedPreferences.Editor edit;
    ArrayList<String> uls=new ArrayList<>();
    String secilen="";
    String gelen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adres);
        ls=(ListView) findViewById(R.id.listView5) ;
        sha = getSharedPreferences("xmlDosya", MODE_PRIVATE);
        edit = sha.edit();
         gelen=sha.getString("adres","");
        uls.add(gelen);
        ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplication(),android.R.layout.test_list_item,uls);
        ls.setAdapter(adp);
edit.commit();

    }

       // String url=" http://jsonbulut.com/json/addressList.php?ref=7b7392076900968d8e4ad78351ad55d3&musterilerID=35";
       //new girisYap(url,this).execute();





    public void fncAdres(View v){
        Intent i=new Intent(adres.this,adresekle.class);
        startActivity(i);
    }
    public void fncSilAdres(View v){

        uls.clear();
        ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplication(),android.R.layout.test_list_item,uls);
        ls.setAdapter(adp);
  sha.edit().clear();
    }}
/*
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
            uls.clear();
            try {
                JSONObject obj = new JSONObject(data);
                JSONArray ar = obj.getJSONArray("announcements");
JSONArray arr=ar.getJSONArray(0);

                for (int i=0; i<arr.length(); i++) {
                    JSONObject oj = arr.getJSONObject(i);
                   // JSONObject dt = oj.getJSONObject(i);
                   if(oj.getString("musterilerID").equals("35")){


            }}catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}*/
