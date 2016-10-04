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

public class Giris extends AppCompatActivity {

    EditText mail, sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        mail = (EditText) findViewById(R.id.txtMail);
        sifre = (EditText) findViewById(R.id.txtSifre);


    }
    public void fncGiris(View v) {

        String ma = mail.getText().toString().trim();
        String si = sifre.getText().toString().trim();
        String url = "http://jsonbulut.com/json/userLogin.php?ref=7b7392076900968d8e4ad78351ad55d3&userEmail="+ma+"&userPass="+si+"&face=no";
        new girisYap(url,this).execute();


    }

    public void fncKayit(View v) {
        Intent i = new Intent(Giris.this, Kayit.class);
        startActivity(i);
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
                    Intent i = new Intent(Giris.this, AnaKategoriler.class);
                    startActivity(i);
                    // giriş başarışı aşağıdaki işlemleri yap
                    JSONObject dt = oj.getJSONObject("bilgiler");
                    Log.d("Giriş ID",dt.getString("userId"));

                }else {
                    Toast.makeText(getApplication(), oj.getString("mesaj"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



}


