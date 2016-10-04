package comt.example.java3.eticaretprojesi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class AnaKategoriler extends AppCompatActivity {
    ListView ls;
    Spinner sp;
    String []dizi={"    ","Adreslerim","Ayarlar","Çıkış"};
    final List<String> uls=new ArrayList<>();
    static String gelen;
    List<String> ye=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_kategoriler);
        ls=(ListView)findViewById(R.id.listView);
        sp=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adpp=new ArrayAdapter<>(this,android.R.layout.test_list_item,dizi);
        sp.setAdapter(adpp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String secilenDeger = sp.getItemAtPosition(i).toString();
                Toast.makeText(AnaKategoriler.this, "seçilen"+secilenDeger, Toast.LENGTH_SHORT).show();
if(secilenDeger.equals("Ayarlar")){
    Intent ii= new Intent(AnaKategoriler.this,ayarlar.class);
    startActivity(ii);

}else if(secilenDeger.equals("Adreslerim")){
                    Intent iii= new Intent(AnaKategoriler.this,adres.class);
                    startActivity(iii);
                }else if(secilenDeger.equals("Çıkış")){
    finish();
    Intent ii=new Intent(AnaKategoriler.this,Giris.class);
    startActivity(ii);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        String url="http://jsonbulut.com/json/companyCategory.php?ref=7b7392076900968d8e4ad78351ad55d3";
        new girisYap(url,this).execute();

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ii=new Intent(AnaKategoriler.this,AltKategoriler.class);
                gelen=ye.get(i);
                ii.putExtra("gelen",gelen);
                Toast.makeText(AnaKategoriler.this, uls.get(i), Toast.LENGTH_SHORT).show();
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
            uls.clear();
            try {
                JSONObject obj = new JSONObject(data);
                JSONArray ar = obj.getJSONArray("Kategoriler");
                JSONObject oj = ar.getJSONObject(0);
               final JSONArray ur = oj.getJSONArray("Categories");
                for (int i=0; i<ur.length();i++) {
                    JSONObject dt = ur.getJSONObject(i);
                    if(dt.getString("TopCatogryId").equals("0")){
                   uls.add(dt.getString("CatogryName"));
                        ye.add(dt.getString("CatogryId"));

                }}
                ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplication(),android.R.layout.test_list_item,uls);
                ls.setAdapter(adp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
