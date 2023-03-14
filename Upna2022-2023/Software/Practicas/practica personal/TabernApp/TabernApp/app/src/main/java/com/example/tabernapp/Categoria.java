package com.example.tabernapp;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabernapp.Models.Item;
import com.example.tabernapp.Models.Tipo;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends AppCompatActivity {

    // View components
    TabernAppApplication app;

    // Application data manipulation structures
    List<Item> catalogue = new ArrayList<>();
    Item selectedItem;
    Bundle bundle;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_view);

        // Retrieve selected item on catalogue
        app = (TabernAppApplication) getApplicationContext();
        catalogue = app.getCatalogo();
        bundle = getIntent().getExtras();
        pos = bundle.getInt("listPosition");
        selectedItem = catalogue.get(pos);

        displayCategory(selectedItem.getTipo());

    }

    /**
     * Displays the specified category in the layout.
     * @param categoryType category to implement
     */
    private void displayCategory(Tipo categoryType) {
        TextView card1Title, card2Title, card3Title, card4Title;
        TextView card1Stock, card2Stock, card3Stock, card4Stock;
        TextView card1Ready, card2Ready, card3Ready, card4Ready;
        ImageView header;
        ImageButton imgBtn1, imgBtn2, imgBtn3, imgBtn4;

        // Retrieve all category values
        app.startCategory(categoryType);
        List<Item> category = app.getCategoria();

        // Product name manipulation
        card1Title = (TextView) findViewById(R.id.textElem1);
        card2Title = (TextView) findViewById(R.id.textElem2);
        card3Title = (TextView) findViewById(R.id.textElem3);
        card4Title = (TextView) findViewById(R.id.textElem4);

        card1Title.setText(category.get(0).toString());
        card2Title.setText(category.get(1).toString());
        card3Title.setText(category.get(2).toString());
        card4Title.setText(category.get(3).toString());

        // Product stock manipulation
        card1Stock = (TextView) findViewById(R.id.textInv1);
        card2Stock = (TextView) findViewById(R.id.textInv2);
        card3Stock = (TextView) findViewById(R.id.textInv3);
        card4Stock = (TextView) findViewById(R.id.textInv4);

        card1Stock.setText("Inventario: " + Integer.toString(category.get(0).getStock()));
        card2Stock.setText("Inventario: " + Integer.toString(category.get(1).getStock()));
        card3Stock.setText("Inventario: " + Integer.toString(category.get(2).getStock()));
        card4Stock.setText("Inventario: " + Integer.toString(category.get(3).getStock()));

        // Product number of items ready manipulation
        card1Ready = (TextView) findViewById(R.id.textPrep1);
        card2Ready = (TextView) findViewById(R.id.textPrep2);
        card3Ready = (TextView) findViewById(R.id.textPrep3);
        card4Ready = (TextView) findViewById(R.id.textPrep4);

        int val1 = category.get(0).getCantPreparando();
        int val2 = category.get(1).getCantPreparando();
        int val3 = category.get(2).getCantPreparando();
        int val4 = category.get(3).getCantPreparando();

        if (val1 != -1) card1Ready.setText("Preparacion: " + Integer.toString(val1));
        if (val2 != -1) card1Ready.setText("Preparacion: " + Integer.toString(val2));
        if (val3 != -1) card1Ready.setText("Preparacion: " + Integer.toString(val3));
        if (val4 != -1) card1Ready.setText("Preparacion: " + Integer.toString(val4));

        // Header image manipulation
        header = (ImageView) findViewById(R.id.imgvCabecera);
        int hdr = getResources().getIdentifier("@drawable/" + categoryType.name().toLowerCase() + "_cabecera", null, getPackageName());
        Drawable hdrImg = getResources().getDrawable(hdr);
        header.setImageDrawable(hdrImg);

        // Cards image manipulation
        imgBtn1 = (ImageButton) findViewById(R.id.imgb1);
        imgBtn2 = (ImageButton) findViewById(R.id.imgb2);
        imgBtn3 = (ImageButton) findViewById(R.id.imgb3);
        imgBtn4 = (ImageButton) findViewById(R.id.imgb4);

        int crd1 = getResources().getIdentifier("@drawable/" + categoryType.name().toLowerCase() + "_card1", null, getPackageName());
        int crd2 = getResources().getIdentifier("@drawable/" + categoryType.name().toLowerCase() + "_card2", null, getPackageName());
        int crd3 = getResources().getIdentifier("@drawable/" + categoryType.name().toLowerCase() + "_card3", null, getPackageName());
        int crd4 = getResources().getIdentifier("@drawable/" + categoryType.name().toLowerCase() + "_card4", null, getPackageName());

        Drawable img1 = getResources().getDrawable(crd1);
        Drawable img2 = getResources().getDrawable(crd2);
        Drawable img3 = getResources().getDrawable(crd3);
        Drawable img4 = getResources().getDrawable(crd4);

        imgBtn1.setImageDrawable(img1);
        imgBtn2.setImageDrawable(img2);
        imgBtn3.setImageDrawable(img3);
        imgBtn4.setImageDrawable(img4);
    }
}