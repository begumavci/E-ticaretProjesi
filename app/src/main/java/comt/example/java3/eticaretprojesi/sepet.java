package comt.example.java3.eticaretprojesi;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class sepet extends AppCompatActivity {
DB db=new DB(this);
    ListView list;
    String giden1,gelen1,id11;
    ArrayList<String> ls=new ArrayList<>();
    static double top;

String secilen="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepet);
        list=(ListView) findViewById(R.id.listView4) ;
        gelen1=getIntent().getExtras().getString("gelen");
        giden1=getIntent().getExtras().getString("giden");
          id11=getIntent().getExtras().getString("id1");

final String[] ls={gelen1,giden1};
        dataGetir();

        ArrayAdapter<String> veri=new ArrayAdapter<String>(this,android.R.layout.test_list_item,ls);
        list.setAdapter(veri);




    }
    public void fncKaydet(View v){
        String a=gelen1.trim();
        String b=giden1.trim();
        db.yaz().execSQL("insert into sepet values (null,'"+a+"','"+b+"')");

        dataGetir();

    }

    public void fncSil(View v){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


               secilen=ls.get(i);
                Toast.makeText(sepet.this, "seçilen:"+secilen, Toast.LENGTH_SHORT).show();
            }
        });
        int silSonuc=db.sil("sepet","urunAdi",secilen);
        if(silSonuc>0){
            Toast.makeText(sepet.this, "Silme İşlemi Başarılı", Toast.LENGTH_SHORT).show();

    }
        dataGetir();
}
    public void fncSiparis(View v) {
        dataGetir();
        Intent i=new Intent(sepet.this,siparisTamamla.class);
        startActivity(i);
        Toast.makeText(sepet.this, "Sipariş Tamamlandı", Toast.LENGTH_SHORT).show();

    }


    public void fncSiparisGit(View v) {
        //dataGetir();
        Intent i=new Intent(sepet.this,siparisTamamla.class);
        startActivity(i);
        Toast.makeText(sepet.this, "Sipariş Tamamlandı", Toast.LENGTH_SHORT).show();

    }



    public void dataGetir(){
        top = 0;
        try{
            ls.clear();
            Cursor cr=db.oku().rawQuery("select * from sepet", null);

            while (cr.moveToNext()){
                ls.add(cr.getString(1));
                top+=Double.valueOf(cr.getString(2));
            }
            ArrayAdapter<String> adp= new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ls);
            list.setAdapter(adp);
        }catch (Exception ex){
            Log.d("Hata: ",ex.toString());
        }
    }



}


