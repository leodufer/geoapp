package caceres.ernesto.py.edu.uninter.geoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Rocio on 24/11/2015.
 */
public class FotoAdapter extends BaseAdapter {

    List<String> fotos;
    Context context;

    public FotoAdapter(List<String> fotos, Context context) {
        this.fotos = fotos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fotos.size();
    }

    @Override
    public Object getItem(int position) {
        return fotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView==null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.foto_item,null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewFoto);
        imageView.setImageBitmap(decodeImage64(fotos.get(position)));
        return view;
    }

    public Bitmap decodeImage64(String strBase64) {
        byte[] decodedString = Base64.decode(strBase64.substring(strBase64.indexOf(",") + 1).getBytes(), 0);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
