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
import java.util.List;

public class AltKategoriler extends AppCompatActivity {
    SharedPreferences sha;
    SharedPreferences.Editor edit;
    ListView ls;
    List<String> al=new ArrayList<>();
    List<String> ye=new ArrayList<>();
    String gel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_kategoriler);
        ls=(ListView)findViewById(R.id.listView2);
        String url="http://jsonbulut.com/json/companyCategory.php?ref=7b7392076900968d8e4ad78351ad55d3";
        new girisYap(url,this).execute();
        sha = getSharedPreferences("xmlDosya", MODE_PRIVATE);
        edit = sha.edit();



        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent ii=new Intent(AltKategoriler.this,Urunler.class);


                //gel=al.get(i);
                //ii.putExtra("gel",gel);
                //Toast.makeText(AltKategoriler.this, al.get(i), Toast.LENGTH_SHORT).show();
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
            al.clear();
            try {
            String gelenn=getIntent().getExtras().getString("gelen");
                JSONObject obj = new JSONObject(data);
                JSONArray ar = obj.getJSONArray("Kategoriler");
                JSONObject oj = ar.getJSONObject(0);
                JSONArray ur = oj.getJSONArray("Categories");
                for (int i=0; i<ur.length();i++) {
                    JSONObject dt = ur.getJSONObject(i);
                    if(dt.getString("TopCatogryId").equals(gelenn)  ){
                        al.add(dt.getString("CatogryName"));
                      gel=  dt.getString("CatogryName");
                        edit.putString("gel",gel);

                    }}
                ArrayAdapter adp=new ArrayAdapter(getApplication(),android.R.layout.test_list_item,al);
                ls.setAdapter(adp);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
