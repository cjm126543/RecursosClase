package com.example.tabernapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tabernapp.Models.Item;

import java.util.List;
import java.util.Vector;

public class ListAdapterCustom extends BaseAdapter {

    // Attributes
    private final Activity actividad;
    private final List<Item> lista;

    // Constructor
    public ListAdapterCustom(Activity act, List<Item> list) {
        super();
        this.actividad = act;
        this.lista = list;
    }

    // Getters
    public View getView(int position, View convertView, ViewGroup parent) {
        TabernAppApplication app = (TabernAppApplication) actividad.getApplicationContext();

        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.row_layout, null, true);
        TextView textView = (TextView) view.findViewById(R.id.listText);
        textView.setText(app.getCatalogo().get(position).toString());

        ImageView imageView = (ImageView) view.findViewById(R.id.imgIcono);
        int imgId = actividad.getResources().getIdentifier("@drawable/" +
                app.getCatalogo().get(position).toString().toLowerCase() + "_logo",
                null, actividad.getPackageName());
        Drawable img = actividad.getResources().getDrawable(imgId);
        imageView.setImageDrawable(img);

        return view;
    }

    public int getCount() {
        return lista.size();
    }

    public Object getItem(int arg0) {
        return lista.get(arg0);
    }

    public long getItemId(int position) {
        return position;
    }
}
