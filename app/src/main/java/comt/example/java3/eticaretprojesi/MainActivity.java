package comt.example.java3.eticaretprojesi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sha;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sha = getSharedPreferences("xmlDosyaAdi", MODE_PRIVATE);
        edit = sha.edit();

        String i = sha.getString("mail", "");
        String ii = sha.getString("sifre", "");

        if (!i.equals("") && !ii.equals("") ) {
            Intent iii = new Intent(MainActivity.this, AnaKategoriler.class);
            startActivity(iii);
        }else{
            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        Intent i = new Intent(MainActivity.this, Giris.class);
                        startActivity(i);

                    } catch (Exception ex) {

                    } finally {
                        finish();
                    }

                }
            };
            th.start();
        }}

}
