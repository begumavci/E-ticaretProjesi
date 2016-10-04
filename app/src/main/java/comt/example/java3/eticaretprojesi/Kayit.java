package comt.example.java3.eticaretprojesi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class Kayit extends AppCompatActivity {

    EditText txtadi,txtsoyadi,txttelefon,txtmail,txtsifre;
    SharedPreferences sha;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sha=getSharedPreferences("xmlDosyaAdi",MODE_PRIVATE);
        edit=sha.edit();

        txtadi = (EditText) findViewById(R.id.ktxtAdi);
        txtsoyadi = (EditText) findViewById(R.id.ktxtSoyadi);
        txttelefon = (EditText) findViewById(R.id.ktxtTel);
        txtmail = (EditText) findViewById(R.id.ktxtMail);
        txtsifre = (EditText) findViewById(R.id.ktxtSifre);
    }
    public void fncKayitYap(View v) {

        String adi = txtadi.getText().toString().trim();
        String soyadi = txtsoyadi.getText().toString().trim();
        String tel = txttelefon.getText().toString().trim();
        String mail = txtmail.getText().toString().trim();
        String sifre = txtsifre.getText().toString().trim();
        String url = "http://jsonbulut.com/json/userRegister.php?ref=7b7392076900968d8e4ad78351ad55d3&userName="+adi+"&userSurname="+soyadi+"&userPhone="+tel+"&userMail="+mail+"&userPass="+sifre+"";
        new girisYap(url,this).execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(getParentActivityIntent()==null){
                    onBackPressed();
                }
        }
        return super.onOptionsItemSelected(item);
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
            try {
                JSONObject obj = new JSONObject(data);
                JSONArray arr = obj.getJSONArray("user");
                JSONObject oj = arr.getJSONObject(0);
                // denetim yapılıyor
                if(oj.getBoolean("durum")) {
                    Toast.makeText(getApplication(), oj.getString("mesaj"), Toast.LENGTH_SHORT).show();
                    // kayıt işlemi başarışı aşağıdaki işlemleri yap
                    Log.d("Giriş ID",oj.getString("kullaniciId"));
                    Intent i=new Intent(Kayit.this,AnaKategoriler.class);
                    startActivity(i);
                    edit.putString("kulid",oj.getString("kullaniciId"));
                    edit.putString("mail",oj.getString("userMail"));
                    edit.putString("sifre",oj.getString("userPass"));
                    edit.commit();

                }else {
                    Toast.makeText(getApplication(), oj.getString("mesaj"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
