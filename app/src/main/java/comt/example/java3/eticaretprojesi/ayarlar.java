package comt.example.java3.eticaretprojesi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class ayarlar extends AppCompatActivity {
EditText ad,soyad,mail,telefon,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        ad=(EditText) findViewById(R.id.editText7);
        soyad=(EditText) findViewById(R.id.editText8);
        mail=(EditText) findViewById(R.id.editText9);
        telefon=(EditText) findViewById(R.id.editText10);
        pass=(EditText) findViewById(R.id.editText11);

    }
public void fncGuncelle(View v){
    String adi = ad.getText().toString().trim();
    String soyadi = soyad.getText().toString().trim();
    String mail1 = mail.getText().toString().trim();
    String tel = telefon.getText().toString().trim();
    String sifre = pass.getText().toString().trim();
    String url = "http://jsonbulut.com/json/userSettings.php?ref=7b7392076900968d8e4ad78351ad55d3&userName="+adi+"&userSurname="+soyadi+"&userMail="+mail1+"&userPhone="+tel+"&userPass="+sifre+"&userId=35";
    new guncelle(url,this).execute();
}
    class guncelle extends AsyncTask<Void,Void,Void> {

        private ProgressDialog pro;
        String data = "";
        String url = "";
        public guncelle(String url, Activity ac) {
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
                if(oj.getBoolean("durum")==false) {
                    Toast.makeText(getApplication(),"Güncelleme  Başarılı", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {

                    Toast.makeText(getApplication(),  oj.getString("mesaj"), Toast.LENGTH_SHORT).show();
 }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }}}

