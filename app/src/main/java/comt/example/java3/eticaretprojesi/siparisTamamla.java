package comt.example.java3.eticaretprojesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class siparisTamamla extends AppCompatActivity {
    DB db=new DB(this);
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_tamamla);
        t=(TextView) findViewById(R.id.txtToplam);
       t.setText(String.valueOf(sepet.top));
        Log.d("sdasd",""+sepet.top);
        System.out.println("adfsdfsdfsdfsd " + sepet.top);

        // herşey bittikten sonra
        temizle();
    }


    // silme işlemi
    public void temizle() {
        try {
            db.yaz().execSQL("DELETE FROM sepet;");
        }catch (Exception ex) {

        }
    }
}
