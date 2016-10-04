package comt.example.java3.eticaretprojesi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class urunAdapter extends BaseAdapter {

    private LayoutInflater inf;
    private List<Urun> urls;

    private Activity ac;
    public urunAdapter(Activity ac, List<Urun> urls){
        inf =(LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.urls=urls;

        this.ac=ac;

    }



    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int i) {
        return urls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v;
        v=inf.inflate(R.layout.row,null);
        TextView vtxt=(TextView)v.findViewById(R.id.txtBaslik);
        TextView vtxt1=(TextView)v.findViewById(R.id.txtFiyat);
        TextView vtxt2=(TextView)v.findViewById(R.id.txtAciklama);
        TextView vtxt3=(TextView)v.findViewById(R.id.txtDetay);
        ImageView vresim=(ImageView) v.findViewById(R.id.resim);


        Urun ul=urls.get(i);
        vtxt.setText(ul.getBaslik());
        vtxt1.setText(ul.getFiyat());
        vtxt2.setText(ul.getAciklama());
        vtxt3.setText(ul.getDetay());
        Picasso.with(ac).load(ul.getResimUrl()).into(vresim);
        return v;
    }
}
